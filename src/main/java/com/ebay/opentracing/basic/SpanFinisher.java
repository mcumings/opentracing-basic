/*
 * Copyright (c) 2017-2018 eBay Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ebay.opentracing.basic;

import java.util.concurrent.TimeUnit;

final class SpanFinisher<T> {
    private final FinishedSpanReceiver<T> receiver;

    SpanFinisher(FinishedSpanReceiver<T> receiver) {
        this.receiver = receiver;
    }

    void finish(MutableSpanData<T> spanState) {
        finish(spanState, TimeUnit.MILLISECONDS, System.currentTimeMillis());
    }

    void finish(MutableSpanData<T> spanState, TimeUnit finishTimeUnit, long finishTime) {
        spanState.setFinishTime(finishTimeUnit, finishTime);
        receiver.spanFinished(spanState);
    }

}
