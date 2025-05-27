package sec03;

import app.Database;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.eq;
public class FindOneTest {
    public static void main(String[] args) {
        MongoCollection<Document> collection = Database.getCollection("todo");

        String id = "6835279a61b20c6dab486989";

        // ObjectId : 도큐먼트(document)의 기본 고유 식별자
        /*
        * Bson : Binary Json
        * */
        // db.users.find({age: {$gt:20}}) -> 조건문에 해당하는 쿼리
        Bson query = eq("_id", new ObjectId(id));

        // 조건에 해당하는 Document 찾기
        // .first() : 찾은 Document들 중 첫번째 Document만 반환
        Document doc = collection.find(query).first();

        // ==> findByIdResult : Document{{_id=6835279a61b20c6dab486989, title=MongoDB, desc=MongoDB 공부하기, done=false}}
        System.out.println("==> findByIdResult : " + doc);

        Database.close();
    }
}