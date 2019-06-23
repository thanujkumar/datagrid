package basics.writethrough_cache;

/**
 * Write-through cache: This holds entries until they are invalidated by subsequent updates
 * <p>
 * In practice, an ideal cache will feature a combination of both the features so that the entries will be held for a
 * known maximum time, but will also pass around invalidations as changes are made.
 * <p>
 * The first major feature of Hazelcast is its masterless nature. Each node is configured to be functionally the
 * same and operates in a peer-to-peer manner. The oldest node in the cluster is the de facto leader.
 * This node manages the membership by automatically making decisions as to which node is responsible for which data.
 * In this way, as the new nodes join in or drop out, the process is repeated and the cluster rebalances accordingly.
 * This makes it incredibly simple to get Hazelcast up and running, as the system is self-discovering,
 * self-clustering, and works straight out of the box
 *
 * <p>
 * <ol>
 * <li>Standard utility collections:</li>
 * <ul>
 * <li>Map: Key-value pairs
 * <li>List: A collection of objects
 * <li>Set: Non-duplicated collection
 * <li>Queue: Offer/poll FIFO collection
 * </ul>
 * <li>Specialized collection:</li>
 * Multi-Map: Key–collection pairs
 * <li>Lock: Cluster wide mutex</li>
 * <li>Topic: Publish and subscribe messaging</li>
 * <li>Concurrency utilities:</li>
 * <ul>
 * <li>AtomicNumber: Cluster-wide atomic counter
 * <li>IdGenerator: Cluster-wide unique identifier generation
 * <li>Semaphore: Concurrency limitation
 * <li>CountdownLatch: Concurrent activity gatekeeping
 * </ul>
 * <li>Listeners: This notifies the application as things happen</li>
 * </ol>
 * </p>
 */
public class WriteThroughCache {
}
