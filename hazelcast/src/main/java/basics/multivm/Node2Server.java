package basics.multivm;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.flakeidgen.FlakeIdGenerator;

import java.util.Map;

public class Node2Server {
    private static HazelcastInstance node2;


        public static void main(String[] args) {
            node2 = Hazelcast.newHazelcastInstance();

            //We will create data in server for node1 to distributed Map
            Map<Long, String> map = node2.getMap("node2");

            FlakeIdGenerator idGenerator = node2.getFlakeIdGenerator("id2");
            for (int i = 0; i < 10; i++) {
                map.put(idGenerator.newId(), "Node2Message" + i);
            }
        }

}
