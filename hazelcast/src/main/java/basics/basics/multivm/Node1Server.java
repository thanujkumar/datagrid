package basics.basics.multivm;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.flakeidgen.FlakeIdGenerator;

import java.util.Map;

/**
 * By default, Hazelcast uses multicast for discovering other members that can form a cluster.
 * If multicast is not a preferred way of discovery for environment, then we can configure Hazelcast for full TCP/IP
 * cluster.
 */
public class Node1Server {
    static HazelcastInstance node1;


    public static void main(String[] args) {
        node1 = Hazelcast.newHazelcastInstance();

        //We will create data in server for node1 to distributed Map
        Map<Long, String> map = node1.getMap("node1");

        FlakeIdGenerator idGenerator = node1.getFlakeIdGenerator("id2");
        for (int i = 0; i < 10; i++) {
            map.put(idGenerator.newId(), "Node1Message" + i);
        }
    }
}
