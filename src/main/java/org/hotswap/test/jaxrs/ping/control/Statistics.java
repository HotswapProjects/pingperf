package org.hotswap.test.jaxrs.ping.control;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Statistics {

    private static final int BUCKETS_SECS = 10;
    private static final int BUCKETS_PER_SECS = 4;
    private static final int TOTAL_BUCKETS = BUCKETS_SECS * BUCKETS_PER_SECS;

    private static class StatisticRec {

        private int buckets[] = new int[TOTAL_BUCKETS];

        private int totalRequests;
        private long totalNonos;
        private long lastBucket;

        public StatisticRec() {
            for (int i = 0; i < TOTAL_BUCKETS; i++) {
                buckets[i] = 0;
            }
        }
    }

    private StatisticRec statistics;

    @PostConstruct
    public void init() {
        statistics = new StatisticRec();
    }

    public int getTotalRequests() {
        return statistics.totalRequests;
    }

    public long getTotalNanos() {
        return statistics.totalNonos;
    }

    public double getAvgThroughput() {
       return doGetAvgThroughput(statistics);
    }

    private synchronized double doGetAvgThroughput(StatisticRec stat) {
        if (stat.lastBucket > 0) {
            long curBucket = System.currentTimeMillis() * BUCKETS_PER_SECS / 1000;
            shuffleBucket(stat, curBucket);

            int tc = 0;
            for (int i = 0; i < TOTAL_BUCKETS; i++) {
                tc += stat.buckets[i];
            }
            return Math.round(100.0 * tc / (BUCKETS_SECS - 0.5)) / 100.0;
        }
        return 0.0;
    }

    public void updateStatistic(long nanos) {
        doReport(statistics, nanos);
    }

    private synchronized void doReport(StatisticRec stat, long nanos) {

        stat.totalRequests ++;
        stat.totalNonos += nanos;

        long curBucket = System.currentTimeMillis() * BUCKETS_PER_SECS / 1000;
        shuffleBucket(stat, curBucket);
        stat.buckets[(int) (curBucket % TOTAL_BUCKETS)] ++;
    }

    private void shuffleBucket(StatisticRec stat, long bucket) {
        if (bucket != stat.lastBucket) {
            long indx = stat.lastBucket + 1;
            if (bucket - (TOTAL_BUCKETS - 1) > indx) {
                indx = bucket - (TOTAL_BUCKETS - 1);
            }
            for(;indx <= bucket;indx++) {
                stat.buckets[(int) (indx % TOTAL_BUCKETS)] = 0;
            }
            stat.lastBucket = bucket;
        }
    }
}