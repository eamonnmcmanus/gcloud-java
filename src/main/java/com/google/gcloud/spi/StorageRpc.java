/* * Copyright 2015 Google Inc. All Rights Reserved. * * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * *       http://www.apache.org/licenses/LICENSE-2.0 * * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. */package com.google.gcloud.spi;import com.google.api.services.storage.model.Bucket;import com.google.api.services.storage.model.StorageObject;import com.google.gcloud.storage.BlobReadChannel;import com.google.gcloud.storage.BlobWriteChannel;import com.google.gcloud.storage.StorageServiceException;import java.util.Iterator;import java.util.Map;public interface StorageRpc {  enum Option {    PREDEFINED_ACL("predefinedAcl"),    PREDEFINED_DEFAULT_OBJECT_ACL("predefinedDefaultObjectAcl"),    IF_METAGENERATION_MATCH("ifMetagenerationMatch"),    IF_GENERATION_MATCH("ifGenerationMatch");    private final String value;    Option(String value) {      this.value = value;    }    public String value() {      return value;    }  }  Bucket create(Bucket bucket, Map<Option, ?> options) throws StorageServiceException;  StorageObject create(StorageObject object, byte[] content, Map<Option, ?> options)      throws StorageServiceException;  Iterator<Bucket> list() throws StorageServiceException;  Iterator<StorageObject> list(String bucket, String prefix, String delimiter, String cursor,      boolean versions, long limit) throws StorageServiceException;  Bucket get(Bucket bucket, Map<Option, ?> options) throws StorageServiceException;  StorageObject get(StorageObject object, Map<Option, ?> options)      throws StorageServiceException;  Bucket patch(Bucket bucket, Map<Option, ?> options) throws StorageServiceException;  StorageObject patch(StorageObject storageObject, Map<Option, ?> options)      throws StorageServiceException;  void delete(Bucket bucket, Map<Option, ?> options) throws StorageServiceException;  void delete(StorageObject object, Map<Option, ?> options) throws StorageServiceException;  StorageObject compose(Iterable<StorageObject> sources, StorageObject target,      Map<Option, ?> targetOptions) throws StorageServiceException;  StorageObject copy(StorageObject source, Map<Option, ?> sourceOptions,      StorageObject target, Map<Option, ?> targetOptions) throws StorageServiceException;  byte[] load(StorageObject storageObject, Map<Option, ?> options)      throws StorageServiceException;  BlobReadChannel reader(StorageObject from, Map<Option, ?> options)      throws StorageServiceException;  BlobWriteChannel writer(StorageObject to, Map<Option, ?> options)      throws StorageServiceException;}