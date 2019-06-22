package basics.multivm.configs;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.Map;

public class Client2 {

    public static void main(String[] args) {
        ClientConfig clientConfig = new ClientConfig();
        ClientNetworkConfig clientNetworkConfig = clientConfig.getNetworkConfig();
        clientNetworkConfig.addAddress("127.0.0.1:5701");
        HazelcastInstance hzClient = HazelcastClient.newHazelcastClient(clientConfig);

        IMap<Long, String> map = hzClient.getMap("node1");

        System.out.println("-------------------Node1 Map-----------------");
        for (Map.Entry<Long, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        map = hzClient.getMap("node2");

        System.out.println("-------------------Node2 Map-----------------");
        for (Map.Entry<Long, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        map = hzClient.getMap("node3");

        System.out.println("-------------------Node3 Map-----------------");
        for (Map.Entry<Long, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        hzClient.shutdown();
    }

}
