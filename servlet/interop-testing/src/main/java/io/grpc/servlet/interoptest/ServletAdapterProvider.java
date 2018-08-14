/*
 * Copyright 2018 The gRPC Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.grpc.servlet.interoptest;

import io.grpc.servlet.ServletAdapter;
import io.grpc.servlet.ServletServerBuilder;
import io.grpc.testing.integration.TestServiceImpl;
import java.util.concurrent.Executors;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * A ManagedBean that produces an instance of ServletAdapter.
 */
@ApplicationScoped
final class ServletAdapterProvider {
  @Produces
  private ServletAdapter getServletAdapter() {
    return Provider.servletAdapter;
  }

  private static final class Provider {
    static final ServletAdapter servletAdapter = ServletAdapter.Factory.create(
        new ServletServerBuilder()
            .addService(new TestServiceImpl(Executors.newScheduledThreadPool(2))));
  }
}
