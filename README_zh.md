Axelor开源套件
================================

Axelor开源套件减少了业务流程的复杂性并提高了响应速度。由于其模块化设计，您可以从少量功能开始，然后根据需要激活其他模块。
Axelor开源套件包含以下模块：

* 客户关系管理(CRM)
* 销售管理
* 财务和成本管理(F&CM)
* 人力资源管理(HR)
* 项目管理(PM)
* 库存和供应链管理(SCM)
* 生产管理(PMC)
* 多公司、多货币和多语言支持

Axelor开源套件是基于[Axelor开源平台](https://github.com/axelor/axelor-open-platform)

安装
================================

要从源代码编译和运行，您需要克隆 Axelor Open Suite 模块，这是一个
[git子模块](https://git-scm.com/book/en/v2/Git-Tools-Submodules)仓库，使用以下命令：

```bash
$ git clone git@github.com:axelor/open-suite-webapp.git
$ cd open-suite-webapp
$ git checkout master
$ git submodule init
$ git submodule update
$ git submodule foreach git checkout master
$ git submodule foreach git pull origin master
```

您可以在我们的文档中找到更详细的[安装说明](https://docs.axelor.com/abs/5.0/install/index.html)。
