package basics.multivm.configs;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.flakeidgen.FlakeIdGenerator;

import java.util.Map;

/**
 * By default, Hazelcast will try 100 ports to bind. In this example, if we set the value of port as 5701 and limit
 * the port count to 20, as members are joining to the cluster, Hazelcast tries to find ports between 5701 and 5721.
 * <p>
 * If we want to choose to use only one port, we can disable the auto-increment feature of a port by setting
 * auto-increment to false.
 */
public class Node2ProgramaticServer {

     static HazelcastInstance node2;
    public static void main(String[] args) {
        Config config = new Config();
        NetworkConfig network = config.getNetworkConfig();
        network.setPort(5701).setPortCount(20);
        network.setPortAutoIncrement(true);
        JoinConfig join = network.getJoin();
        join.getMulticastConfig().setEnabled(false);
        join.getTcpIpConfig()
                .addMember("localhost").setEnabled(true);

        node2 = Hazelcast.newHazelcastInstance(config);

        //We will create data in server for node1 to distributed Map
        Map<Long, String> map = node2.getMap("node2");

        FlakeIdGenerator idGenerator = node2.getFlakeIdGenerator("id2");
        for (int i = 0; i < 10; i++) {
            map.put(idGenerator.newId(), "Node2ProgramaticServer" + i);
        }
    }
}
