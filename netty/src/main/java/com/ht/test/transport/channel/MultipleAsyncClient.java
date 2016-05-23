package com.ht.test.transport.channel;

import com.ht.test.transport.protocol.Msg;

/**
 * Created by hutao on 16/5/13.
 * 下午4:11
 */
public interface MultipleAsyncClient<T extends Msg> extends Client {
    void openMsgChannel(final MsgChannelId msgChannelId, final Listener listener);

    void closeMsgChannel(final MsgChannelId msgChannelId);

    void asyncRequestWithMultipleResponse(final MsgChannelId msgChannelId, final T msg);

    interface Listener<T> {
        void onResponse(final T msg, final ClientRequestContextImpl context);

        void onException(final Throwable e, final ClientRequestContextImpl context);
    }

    class MsgChannelId {
        private final String id;

        private MsgChannelId(final String id) {
            this.id = id;
        }

        public static MsgChannelId of(final String id) {
            return new MsgChannelId(id);
        }

        public String getId() {
            return id;
        }
    }

    class ClientRequestContextImpl extends ClientRequestContext<MultipleAsyncClient> {
        private final MsgChannelId msgChannelId;

        public ClientRequestContextImpl(final MultipleAsyncClient client,
                                        final MsgChannelId msgChannelId) {
            super(client);
            this.msgChannelId = msgChannelId;
        }

        public MsgChannelId getMsgChannelId() {
            return msgChannelId;
        }
    }
}
