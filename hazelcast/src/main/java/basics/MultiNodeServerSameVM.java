package basics;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.flakeidgen.FlakeIdGenerator;

import java.util.Map;

/*
 Both node runs in same JVM, not different

By default, Hazelcast uses multicast for discovering other members that can form a cluster.
If multicast is not a preferred way of discovery for our environment, then we can configure Hazelcast for full TCP/IP
cluster.

When we start the hazel server application, we can see the flowing text in the console which means that we create a
new Hazelcast node in our JVM which will have to join the cluster.
 */

public class MultiNodeServerSameVM {

    static HazelcastInstance node1, node2;


    public static void main(String[] args) {
        node1 = Hazelcast.newHazelcastInstance();
        node2 = Hazelcast.newHazelcastInstance();

        //We will create data in server for node1 to distributed Map
        Map<Long, String> map = node1.getMap("data");

        FlakeIdGenerator idGenerator = node1.getFlakeIdGenerator("newid");
        for (int i = 0; i < 10; i++) {
            map.put(idGenerator.newId(), "Message" + i);
        }
    }
}
