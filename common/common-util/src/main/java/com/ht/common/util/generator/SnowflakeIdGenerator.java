package com.ht.common.util.generator;

import com.google.common.base.Preconditions;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author hutao <胡涛, 123406956@qq.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 2016/12/9
 * @time 下午2:50
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
public class SnowflakeIdGenerator {
    private final long workerId;
    private final int workerIdBits;
    private final int sequenceBits;
    private final int workerIdShift;
    private final int timestampLeftShift;
    private final static int DEFAULT_WORKER_ID_BITS = 10;
    private final static int DEFAULT_SEQUENCE_BITS = 12;
    private AtomicReference<SequenceHolder> atomicSequenceHolder;

    private SnowflakeIdGenerator(final long workerId,
                                 final int workerIdBits,
                                 final int sequenceBits) {
        Preconditions.checkState(workerId > 0 && workerId <= ((1 << workerIdBits) - 1));
        this.workerId = workerId;
        this.workerIdBits = workerIdBits;
        this.sequenceBits = sequenceBits;
        this.workerIdShift = sequenceBits;
        this.timestampLeftShift = sequenceBits + workerIdBits;
        this.atomicSequenceHolder = new AtomicReference<>(new SequenceHolder(
                System.currentTimeMillis(), new AtomicLong(0)));
    }

    public static SnowflakeIdGenerator of(final long workerId) {
        return of(workerId, DEFAULT_WORKER_ID_BITS, DEFAULT_SEQUENCE_BITS);
    }

    public static SnowflakeIdGenerator of(final long workerId,
                                          final int workerIdBits,
                                          final int sequenceBits) {
        return new SnowflakeIdGenerator(workerId, workerIdBits, sequenceBits);
    }

    public long nextId() {
        final long currentTimeMillis = System.currentTimeMillis();
        final long sequence = getSequence(currentTimeMillis);
        return currentTimeMillis << this.timestampLeftShift | this.workerId << this.workerIdShift | sequence;
    }

    private long getSequence(final long currentTimeMillis) {
        final SequenceHolder sequenceHolder = this.atomicSequenceHolder.get();
        final long lastCurrentTimeMillis = sequenceHolder.getTimestamp();
        if (currentTimeMillis == lastCurrentTimeMillis) {
            return sequenceHolder.getSequence().incrementAndGet();
        } else if (currentTimeMillis > lastCurrentTimeMillis) {
            this.atomicSequenceHolder.compareAndSet(sequenceHolder, new SequenceHolder(currentTimeMillis, new AtomicLong(0)));
            return this.atomicSequenceHolder.get().getSequence().incrementAndGet();
        } else {
            throw new RuntimeException("clock moved backwards.Refusing to generate id for milliseconds");
        }
    }

    class SequenceHolder {
        private final long timestamp;
        private final AtomicLong sequence;


        SequenceHolder(final long timestamp, final AtomicLong sequence) {
            this.timestamp = timestamp;
            this.sequence = sequence;
        }

        public long getTimestamp() {
            return this.timestamp;
        }

        public AtomicLong getSequence() {
            return this.sequence;
        }
    }
}
