package org.scoula.travel.dao;
import org.scoula.database.JDBCUtil;
import org.scoula.travel.domain.TravelImageVO;
import org.scoula.travel.domain.TravelVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TravelDaoImpl implements TravelDao {

    Connection conn = JDBCUtil.getConnection();

    @Override
    public void insert(TravelVO travelVO) {
        // database crate
        String sql = "insert into tbl_travel(no,district,title,description,address,phone) values(?,?,?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // query 완성시키기
            pstmt.setLong(1, travelVO.getNo());
            pstmt.setString(2, travelVO.getDistrict());
            pstmt.setString(3, travelVO.getTitle());
            pstmt.setString(4, travelVO.getDescription());
            pstmt.setString(5, travelVO.getAddress());
            pstmt.setString(6, travelVO.getPhone());

            int count = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertImage(TravelImageVO travelImageVO) {
        String sql = "insert into tbl_travel_image(filename, travel_no) values(?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // 1. 쿼리 완성시키기
            pstmt.setString(1, travelImageVO.getFilename());
            pstmt.setLong(2, travelImageVO.getTravelNo());

            // 쿼리 실행
            int row = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 전체 여행지 개수를 조회하는 메서드
     * - 페이징 처리를 위한 총 개수 정보 제공
     * @return 전체 여행지 개수
     */
    @Override
    public int getTotalCount() {

        String sql = "SELECT count(*) FROM tbl_travel";

        // try-with-resources, 여러 객체 등록 가능
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            rs.next(); // 첫 번째 행으로 이동 -> 조회 결과 무조건 1행
            return rs.getInt(1); // 첫 번째 컬럼의 count 값 반환

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 전체 여행지 개수를 조회하는 메서드
     * - 페이징 처리를 위한 총 개수 정보 제공
     * @return 전체 여행지 개수
     */
    @Override
    public List<String> getDistricts() {
        List<String> districts = new ArrayList<>();
        String sql = "SELECT DISTINCT(district) FROM tbl_travel ORDER BY district";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                districts.add(rs.getString("district"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return districts;
    }


    /**
     * 모든 여행지 목록 조회 메서드
     * - 지역별, 제목별로 정렬하여 반환
     *
     * @return 전체 여행지 목록
     */
    @Override
    public List<TravelVO> getTravels() {
        List<TravelVO> travels = new ArrayList<>();
        String sql = "SELECT * FROM tbl_travel ORDER BY district, title";
        try(PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                TravelVO travel = new TravelVO(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                );
                travels.add(travel);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return travels;
    }

    /**
     * 페이지별 여행지 목록 조회 메서드
     * - LIMIT을 사용한 페이징 처리
     *
     * @param page 조회 페이지 번호 (1부터 시작)
     * @return 해당 페이지 여행지 목록 (페이지당 10개)
     */
    @Override
    public List<TravelVO> getTravels(int page) {
        List<TravelVO> travels = new ArrayList<>();

        String sql = "SELECT * FROM tbl_travel ORDER BY district, title LIMIT ?,?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int count = 10; // 한 페이지당 10개씩
            int start = (page - 1) * count; // 시작 인덱스

            pstmt.setInt(1, start);
            pstmt.setInt(2, count);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TravelVO travel = new TravelVO(
                            rs.getLong(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6)
                    );
                    travels.add(travel);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return travels;
    }

    /**
     * 특정 지역 여행지 목록 조회 메서드
     * - getTravles() 메서드 오버로딩
     *
     * @param district 조회할 지역명
     * @return 해당 지역의 여행지 목록
     */
    @Override
    public List<TravelVO> getTravels(String district) {
        List<TravelVO> travels = new ArrayList<>();

        String sql = "SELECT * FROM tbl_travel WHERE district = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, district);

            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TravelVO travel = new TravelVO(
                            rs.getLong(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6)
                    );
                    travels.add(travel);
                }
            }
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }

        return travels;
    }

    /**
     * 특정 여행지의 상세 정보 + 관련 이미지 조회 메서드
     * - LEFT OUTER JOIN을 사용하여 이미지가 없는 여행지도 조회
     * - Optional을 사용하여 결과의 존재 여부 표현
     *
     * @param no 조회할 여행지 번호
     * @return 여행지 상세 정보와 이미지 목록을 포함한 Optional<TravelVO>
     */
    @Override
    public Optional<TravelVO> getTravel(Long no) {
        TravelVO travel = null;

        String sql = """
                    SELECT
                    
                """;


        return Optional.empty();
    }
}