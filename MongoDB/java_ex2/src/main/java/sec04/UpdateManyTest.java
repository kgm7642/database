package sec04;

import app.Database;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;

public class UpdateManyTest {
    public static void main(String[] args) {
        MongoCollection<Document> collection = Database.getCollection("todo");

        // ✅ 여러 ObjectId 리스트 정의
        List<ObjectId> ids = Arrays.asList(
                new ObjectId("68355afb1efcba5655327c01"),
                new ObjectId("68355b65d1a96306d2306574")
        );
        Bson query = in("_id", ids);

        Bson updates = Updates.combine(
                Updates.set("name", "modify name"),
                Updates.currentTimestamp("lastUpdated"));

        // 조건에 맞는 하나의 도큐먼트를 업데이트
        UpdateResult result = collection.updateMany(query, updates);

        // 조건에 맞는 모든 도큐먼트를 한번에 업데이트
//        UpdateResult result = collection.updateMany(query, updates);

        System.out.println("==> UpdateResult : " + result.getModifiedCount());

        Database.close();
    }
}
