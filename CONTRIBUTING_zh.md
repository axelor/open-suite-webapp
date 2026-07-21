# 贡献指南

我们非常欢迎您为我们的源代码做出贡献，使应用程序更加完善。虽然我们尽量减少对贡献的要求，但仍有一些指南希望您遵守：

* [贡献者许可协议](#contributor-license-agreement)
* [报告问题](#reporting-issues)
* [提交贡献](#submitting)
* [行为准则](#code-of-conduct)

## 贡献者许可协议

如果您以个人身份提交代码，则同意[个人贡献者许可协议][individual-cla].
B如果您以实体身份提交代码，则同意[企业贡献者许可协议][corporate-cla].

## 报告问题

在提交问题之前，请先搜索存档，也许您的问题已经得到了解答。
如果您的问题看起来像是一个 bug，并且尚未被报告，请提交一个新的问题。为了帮助我们最大限度地利用时间来修复问题和添加新功能，请不要重复报告相同的问题。提供以下信息将有助于我们更快地处理您的问题：

* **使用场景** – 解释您的使用场景
* **问题概述** – 包括堆栈跟踪
* **版本** – 您正在使用哪个版本？
* **浏览器和操作系统** – 这是所有浏览器的问题吗？
* **重现错误** – 提供一个补丁来重现错误
* **相关问题** – 是否有类似的问题已被报告？
* **建议修复方案** – 您可以指出可能引起问题的代码行或提交

请确保在报告问题时不要发布任何敏感信息。

## 提交贡献

* [Fork仓库](https://help.github.com/articles/fork-a-repo/) the repo.
* 编码!
* 格式化 Java 代码以遵循 Google 代码格式。工具：
  * Gradle 任务：`./gradlew spotlessApply`
  * IDE 插件
    * [Eclipse](https://github.com/google/google-java-format#eclipse)
    * [IntelliJ](https://github.com/google/google-java-format#intellij)
* 您必须创建一个变更日志条目来描述更改。
请参阅 [请参阅changelogs文件夹中的README](https://github.com/axelor/axelor-open-suite/blob/master/changelogs/README.md)
，并按照说明操作。
* 将更改推送到您 fork 的仓库中的主题分支。

* 在开发分支上发起一个 [pull request](http://help.github.com/send-pull-requests/) .
例如，如果问题出现在`master`分支，则选择`dev` 分支；如果问题出现在`5.3`分支，则选择 `5.3-dev`, 依此类推。

## 行为准则

作为本项目的贡献者和维护者，为了营造一个开放、和谐的社区，我们承诺尊重所有通过报告问题、发布功能请求、更新文档、提交 pull request 或补丁以及其他活动为项目做出贡献的人。
我们致力于确保每个人都能在一个没有骚扰的环境中参与本项目，无论其经验水平、性别、性别认同和表达、性取向、残疾、外貌、体型、种族、民族、年龄、宗教或国籍如何。

参与者不可接受的行为示例包括：

* 使用带有性暗示的语言或图像
* 人身攻击
* 骚扰或侮辱/贬低性评论
* 公开或私下骚扰
* 发布他人的私人信息（如物理地址或电子地址），除非得到明确许可
* 其他不道德或不专业的行为

项目维护者有权和责任移除、编辑或拒绝不符合本行为准则的评论、提交、代码、wiki 编辑、问题和其他贡献，或者暂时或永久禁止其他被认为不合适、威胁、冒犯或有害的行为。
通过采用此行为准则，项目维护者承诺公平和一致地将这些原则应用于管理项目的各个方面。未遵守或执行行为准则的项目维护者可能会被永久从项目团队中移除。
此行为准则适用于项目空间内以及当个人代表项目或其社区在公共空间中的行为。
如果您遇到滥用、骚扰或其他不可接受的行为，可以通过[conduct@axelor.com][mail]. 联系项目维护者。所有投诉都将被审查和调查，并根据情况作出必要的和适当的回应。维护者有义务对事件举报人保持保密。

此行为准则是基于[ 贡献者公约][homepage]，
版本 1.3.0，可访问
[http://contributor-covenant.org/version/1/3/0/][version]获取。

[mail]: mailto:conduct@axelor.com
[homepage]: http://contributor-covenant.org
[version]: http://contributor-covenant.org/version/1/3/0/
[individual-cla]: http://axelor.com/cla/individuel-cla/
[corporate-cla]: http://axelor.com/cla/corporate-cla/
