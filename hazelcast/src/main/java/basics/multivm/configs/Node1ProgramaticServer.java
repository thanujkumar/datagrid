package basics.multivm.configs;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.ManagementCenterConfig;
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
 *
 * Adding this node programatically to management center (ensure to run management-center from download or war)
 */
public class Node1ProgramaticServer {

    static HazelcastInstance node1;

    public static void main(String[] args) {
        Config config = new Config();
        ManagementCenterConfig manCenterCfg = config.getManagementCenterConfig();
        manCenterCfg.setEnabled(true);
        manCenterCfg.setUrl("http://localhost:8080/hazelcast-mancenter");
        NetworkConfig network = config.getNetworkConfig();
        network.setPort(5701).setPortCount(20);
        network.setPortAutoIncrement(true);
        JoinConfig join = network.getJoin();
        join.getMulticastConfig().setEnabled(false); //multicast disabled
        join.getTcpIpConfig().addMember("localhost").setEnabled(true); //discover by TC IP on specific network

        node1 = Hazelcast.newHazelcastInstance(config);

        //We will create data in server for node1 to distributed Map
        Map<Long, String> map = node1.getMap("node1");

        FlakeIdGenerator idGenerator = node1.getFlakeIdGenerator("id1");
        for (int i = 0; i < 10; i++) {
            map.put(idGenerator.newId(), "Node1ProgramaticServer" + i);
        }

    }
}
