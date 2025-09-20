/*
 * Implement Router
Solved
Medium
Topics
premium lock icon
Companies
Hint
Design a data structure that can efficiently manage data packets in a network router. Each data packet consists of the following attributes:

source: A unique identifier for the machine that generated the packet.
destination: A unique identifier for the target machine.
timestamp: The time at which the packet arrived at the router.
Implement the Router class:

Router(int memoryLimit): Initializes the Router object with a fixed memory limit.

memoryLimit is the maximum number of packets the router can store at any given time.
If adding a new packet would exceed this limit, the oldest packet must be removed to free up space.
bool addPacket(int source, int destination, int timestamp): Adds a packet with the given attributes to the router.

A packet is considered a duplicate if another packet with the same source, destination, and timestamp already exists in the router.
Return true if the packet is successfully added (i.e., it is not a duplicate); otherwise return false.
int[] forwardPacket(): Forwards the next packet in FIFO (First In First Out) order.

Remove the packet from storage.
Return the packet as an array [source, destination, timestamp].
If there are no packets to forward, return an empty array.
int getCount(int destination, int startTime, int endTime):

Returns the number of packets currently stored in the router (i.e., not yet forwarded) that have the specified destination and have timestamps in the inclusive range [startTime, endTime].
Note that queries for addPacket will be made in increasing order of timestamp.
 */

import java.util.*;

class Router {
  private Queue<int[]> packetQueue;
  private Map<Integer, ArrayList<Integer>> destinationMap;
  private Set<String> packetSet;
  private int memoryLimit;

  public Router(int memoryLimit) {
    this.packetQueue = new LinkedList<>();
    this.destinationMap = new HashMap<>();
    this.packetSet = new HashSet<>();
    this.memoryLimit = memoryLimit;
  }

  public boolean addPacket(int source, int destination, int timestamp) {
    String packetKey = source + "," + destination + "," + timestamp;

    // Check for duplicate packet
    if (packetSet.contains(packetKey))
      return false;

    // Remove oldest packet if memory limit reached
    if (packetQueue.size() >= memoryLimit) {
      int[] removedPacket = packetQueue.poll();
      String removedKey = removedPacket[0] + "," + removedPacket[1] + "," + removedPacket[2];
      packetSet.remove(removedKey);

      // Remove from destination map
      ArrayList<Integer> timestamps = destinationMap.get(removedPacket[1]);
      if (timestamps != null) {
        int idx = Collections.binarySearch(timestamps, removedPacket[2]);
        if (idx >= 0)
          timestamps.remove(idx);
      }
    }

    // Add new packet
    packetSet.add(packetKey);
    packetQueue.offer(new int[] { source, destination, timestamp });
    destinationMap.putIfAbsent(destination, new ArrayList<>());
    destinationMap.get(destination).add(timestamp);

    return true;
  }

  public int[] forwardPacket() {
    if (packetQueue.isEmpty())
      return new int[] {};

    int[] removedPacket = packetQueue.poll();
    String removedKey = removedPacket[0] + "," + removedPacket[1] + "," + removedPacket[2];
    packetSet.remove(removedKey);

    // Remove from destination map
    ArrayList<Integer> timestamps = destinationMap.get(removedPacket[1]);
    if (timestamps != null) {
      int idx = Collections.binarySearch(timestamps, removedPacket[2]);
      if (idx >= 0)
        timestamps.remove(idx);
    }

    return removedPacket;
  }

  public int getCount(int destination, int startTime, int endTime) {
    ArrayList<Integer> timestamps = destinationMap.get(destination);
    if (packetQueue.isEmpty() || timestamps == null)
      return 0;

    int threshold = packetQueue.peek()[2];
    int startIdx = findStartIndex(timestamps, startTime, threshold);
    int endIdx = findEndIndex(timestamps, endTime, threshold);

    if (startIdx == -1 || endIdx == -1 || startIdx > endIdx)
      return 0;

    return endIdx - startIdx + 1;
  }

  private int findStartIndex(ArrayList<Integer> list, int startTime, int threshold) {
    int low = 0, high = list.size() - 1, result = -1;
    while (low <= high) {
      int mid = (low + high) / 2;
      if (list.get(mid) < threshold || list.get(mid) < startTime)
        low = mid + 1;
      else {
        result = mid;
        high = mid - 1;
      }
    }
    return result;
  }

  private int findEndIndex(ArrayList<Integer> list, int endTime, int threshold) {
    int low = 0, high = list.size() - 1, result = -1;
    while (low <= high) {
      int mid = (low + high) / 2;
      if (list.get(mid) < threshold)
        low = mid + 1;
      else if (list.get(mid) > endTime)
        high = mid - 1;
      else {
        result = mid;
        low = mid + 1;
      }
    }
    return result;
  }
}

/**
 * Usage:
 * Router router = new Router(memoryLimit);
 * boolean added = router.addPacket(source, destination, timestamp);
 * int[] forwardedPacket = router.forwardPacket();
 * int count = router.getCount(destination, startTime, endTime);
 */
