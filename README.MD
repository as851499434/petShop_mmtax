
模块划分：
mmtax-admin :系统管理后台端主要代码，包括系统管理后台前后端代码
mmtax-common：公共工具模块
mmtax-framework：系统配置模块
mmtax-generator：代码生成模块
mmtax-quartz：定时任务模块
mmtax-system：service，dao，domain以及mapper文件存放模块

正式使用时，请自行配置数据库和redis链接


打包：使用IDEA开发工具打包时，由于使用的是springboot，无需打包成war包，直接打包成jar包即可。点击右侧工具栏maven-->mmtax-->lifecyle-->clean-->install等待打包完成，发布服务的时候，将mmtax-admin目录下target文件夹中的mmtax-admin.jar发布到服务器即可
