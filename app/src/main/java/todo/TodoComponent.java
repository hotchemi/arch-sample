/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package todo;

import javax.inject.Singleton;

import dagger.Component;
import todo.ui.ItemsFragment;
import todo.ui.ListsFragment;
import todo.ui.NewItemFragment;
import todo.ui.NewListFragment;

@Singleton
@Component(modules = TodoModule.class)
public interface TodoComponent {

  void inject(ListsFragment fragment);

  void inject(ItemsFragment fragment);

  void inject(NewItemFragment fragment);

  void inject(NewListFragment fragment);
}