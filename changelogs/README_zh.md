## 变更内容

#### 概述

`unreleased` 文件夹包含所有尚未发布的变更内容。

在发布时，所有未发布的变更内容将合并到最终的 CHANGELOG.md 文件中。

#### 变更内容条目格式

在 `changelogs/unreleased` 下创建一个新文件。

该文件应为 YAML 格式，如下所示：

````yaml
---
title: 一些文本
type: feature
description: |
  这里是一些描述 包含更多详细信息。
  
  以及关于破坏性变更 和迁移步骤的说明。

  ```
  $ find -iregex ".*domains.*.xml" | xargs -l sed -i 's|cachable="|cacheable="|g'
  ```
````

`title` 描述条目。

`type` 可以是：
* **feature** 表示新功能。
* **change** 表示现有功能的更改。
* **deprecate** 表示即将移除的功能。
* **remove** 表示已移除的功能。
* **fix** 表示任何 bug 修复。
* **security** 表示安全漏洞修复。

`description` 是可选的，应该提供关于更改的详细描述，包括迁移步骤（如果有）。

#### 如何判断是否需要新的变更内容条目

**除了少数特殊情况外，您无需为在 wip 分支中修复的问题添加变更内容条目。**

变更内容需要列出从上一版本到当前版本的更改。当版本处于开发阶段时，新功能可能会引入回归问题。如果我们在版本发布前修复了这些回归问题，那么这些 bug 将不会出现在任何已发布的版本中，因此修复记录不需要出现在变更内容中。但如果我们在修复已发布的版本中的 bug，则必须添加变更内容条目。

### 如何选择条目的类型

-   **如果您在 dev 分支上，大多数情况下类型是 `fix` 或 `change`。**
-   **如果您在 wip 分支上，类型可以是 `change` 或 `feature`（参见上文）。**

`change` 属于当前的“改进”部分：在现有类中添加一个简单字段不是新功能，而是更改。

dev分支上的新功能和wip分支上的修复等特殊情况下可能存在，但应仅在少数情况下发生。

### 变更内容关注应用程序的使用

**不要使用技术字段名称。** 您必须写明更改对应用程序用户的影响。
任何关于更改的技术信息可以在需要时写入提交消息，但在变更内容中应尽量避免。

例如，不要写：

> 当 statusSelect 等于 `STATUS_CANCELED` 时，将 typeSelect 的 hidden 属性更新为 true。

而应写：

> 在发票表单视图中，隐藏取消的发票类型。

#### 生成 CHANGELOG.md

要使用未发布的条目生成 `CHANGELOG.md`，请运行以下Gradle任务：
```
./gradlew generateChangeLog
```

未发布的条目也将自动从 `changelogs/unreleased` 中删除。

可以使用 `--preview` 参数预览生成的变更内容，而不删除或更新文件。

#### 参考资料

* [Keep a Changelog](https://keepachangelog.com/en/1.0.0/)
* [Gitlab: 如何解决CHANGELOG冲突](https://about.gitlab.com/2018/07/03/solving-gitlabs-changelog-conflict-crisis/)
