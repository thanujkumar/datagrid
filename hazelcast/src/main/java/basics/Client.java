package basics;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.Map;

public class Client {
    public static void main(String[] args) {
        ClientConfig config = new ClientConfig();
        GroupConfig groupConfig = config.getGroupConfig();
        groupConfig.setName("dev");
        groupConfig.setPassword("dev-pass");
        HazelcastInstance hzClient
                = HazelcastClient.newHazelcastClient(config);

        IMap<Long, String> map = hzClient.getMap("data");

        for (Map.Entry<Long, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() +" = " + entry.getValue());
        }

        hzClient.getMap("data").put("1", "Message11");

        System.out.println("-------------------changes-----------------");
        for (Map.Entry<Long, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() +" = " + entry.getValue());
        }

        hzClient.shutdown();

    }
}
