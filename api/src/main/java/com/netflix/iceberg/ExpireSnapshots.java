/*
 * Copyright 2017 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.iceberg;

import java.util.List;

/**
 * API for removing old {@link Snapshot snapshots} from a table.
 * <p>
 * This API accumulates snapshot deletions and commits the new list to the table. This API does not
 * allow deleting the current snapshot.
 * <p>
 * When committing, these changes will be applied to the latest table metadata. Commit conflicts
 * will be resolved by applying the changes to the new latest metadata and reattempting the commit.
 * <p>
 * {@link #apply()} returns a list of the snapshots that will be removed.
 */
public interface ExpireSnapshots extends PendingUpdate<List<Snapshot>> {

  /**
   * Expires a specific {@link Snapshot} identified by id.
   *
   * @param snapshotId long id of the snapshot to expire
   * @return this for method chaining
   */
  ExpireSnapshots expireSnapshotId(long snapshotId);

  /**
   * Expires all snapshots older than the given timestamp.
   *
   * @param timestampMillis a long timestamp, as returned by {@link System#currentTimeMillis()}
   * @return this for method chaining
   */
  ExpireSnapshots expireOlderThan(long timestampMillis);

}