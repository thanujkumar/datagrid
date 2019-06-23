package basics;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.console.ConsoleApp;
import com.hazelcast.core.Hazelcast;

/**
 * <p>
 *     <ol>
 *       <li>Standard utility collections:</li>
 *       <ul>
 *            <li>Map: Key-value pairs
 *            <li>List: A collection of objects
 *            <li>Set: Non-duplicated collection
 *            <li>Queue: Offer/poll FIFO collection
 *        </ul>
 *      <li>Specialized collection:</li>
 *           Multi-Map: Key–collection pairs
 *      <li>Lock: Cluster wide mutex</li>
 *      <li>Topic: Publish and subscribe messaging</li>
 *      <li>Concurrency utilities:</li>
 *         <ul>
 *          <li>AtomicNumber: Cluster-wide atomic counter
 *          <li>IdGenerator: Cluster-wide unique identifier generation
 *          <li>Semaphore: Concurrency limitation
 *          <li>CountdownLatch: Concurrent activity gatekeeping
 *          </ul>
 *     <li>Listeners: This notifies the application as things happen</li>
 *    </ol>
 *  </p>
 *
 */
public class RunningConsoleApp {

    public static void main(String[] args) throws Exception {
        //java -cp hazelcast-x.x.x.jar com.hazelcast.console.ConsoleApp
        // ConsoleApp.main(args);
        //each node should be identical in its configuration when starting next

        Config config = new Config();
        ManagementCenterConfig manCenterCfg = config.getManagementCenterConfig();
        manCenterCfg.setEnabled(true);
        manCenterCfg.setUrl("http://localhost:8080/hazelcast-mancenter");
        NetworkConfig network = config.getNetworkConfig();
        network.setPort(5701).setPortCount(20);
        network.setPortAutoIncrement(true);
        JoinConfig join = network.getJoin();
        join.getMulticastConfig().setEnabled(false); //multicast disabled
        join.getTcpIpConfig().addMember("127.0.0.1").setEnabled(true); //discover by TC IP on specific network

        //Above is TCP IP, if other memeber needs to join same configuration is required
        new ConsoleApp(Hazelcast.newHazelcastInstance(config)).start(); //start will start CLI

        //m.put one two - This will be put to default namespace
        //ns capitals
        //m.put IN Delhi
        //m.out US WashingtonDC
        //m.entries -- will list the entries
        //ns default

    }
}
