/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.snia.cdmi.v1.domain;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;
import java.util.Set;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

/**
 * 
 * @author Adrian Cole
 */
public class Container extends CDMIObject {

   public static Builder<?> builder() {
      return new ConcreteBuilder();
   }

   @Override
   public Builder<?> toBuilder() {
      return builder().fromContainer(this);
   }

   public static class Builder<B extends Builder<B>> extends CDMIObject.Builder<B> {

      private Set<String> children = ImmutableSet.of();
      private Map<String, String> metadata = Maps.newHashMap();

      /**
       * @see Container#getChildren()
       */
      public B children(String... children) {
         return children(ImmutableSet.copyOf(checkNotNull(children, "children")));
      }

      /**
       * @see Container#getChildren()
       */
      public B children(Set<String> children) {
         this.children = ImmutableSet.copyOf(checkNotNull(children, "children"));
         return self();
      }

      /**
       * @see Container#getMetadata()
       */
      public B metadata(Map<String, String> metadata) {
         this.metadata = ImmutableMap.copyOf(checkNotNull(metadata, "metadata"));
         return self();
      }

      @Override
      public Container build() {
         return new Container(this);
      }

      public B fromContainer(Container in) {
         return fromCDMIObject(in).children(in.getChildren()).metadata(in.getMetadata());
      }
   }

   private static class ConcreteBuilder extends Builder<ConcreteBuilder> {
   }

   private final Set<String> children;
   private final Map<String, String> metadata;

   protected Container(Builder<?> builder) {
      super(builder);
      this.children = ImmutableSet.copyOf(checkNotNull(builder.children, "children"));
      this.metadata = ImmutableMap.copyOf(checkNotNull(builder.metadata, "metadata"));
   }

   /**
    * Names of the children objects in the container object. Child container objects end with "/".
    */
   public Set<String> getChildren() {
      return children;
   }

   /**
    * Metadata for the container object. This field includes any user and data system metadata
    * specified in the request body metadata field, along with storage system metadata generated by
    * the cloud storage system.
    */
   public Map<String, String> getMetadata() {
      return metadata;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      Container that = Container.class.cast(o);
      return super.equals(that) && equal(this.children, that.children) && equal(this.metadata, that.metadata);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(super.hashCode(), children, metadata);
   }

   @Override
   public ToStringHelper string() {
      return super.string().add("children", children).add("metadata", metadata);
   }

}
