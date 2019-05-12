## 

 示例运行说明

1.  新建数据库xkes
2.  修改数据库连接
3.  运行DemoApplication


## 

 开发说明


### 

 JAVA代码开发规范：详见doc中的 [《阿里巴巴Java开发规范手册.pdf》](doc/阿里巴巴Java开发规范手册.pdf)


### 

 API开发规范


####

 Entity规范

1  所有业务Entity必须继承BaseEntity并生成序列化ID，需加上注解@ApiModel并注   明具体含义
	
```java
	@ApiModel(value="学生信息对象",description = "学生信息对象")
	public class StudentEntity extends BaseEntity{
	private static final long serialVersionUID = 1892183089197941921L;
	}
```

2  需对属性字段进行注释，并加上注解和具体含义：

```
	/**
	学生姓名
	*/
    @ApiModelProperty(value = "学生姓名")
    @JsonIgnore //表示json序列化时忽略此字段
    private String name;
```
3	具体示例：详见StudentEntity.java

####

APIController规范

1、	请求路径格式：”/api/模块名”

2、	必须对该模块进行注解并表明具体含义

```
@Api(value = "XX模块接口")
```

3、	具体示例：详见StudentAPIController.java

####

APIcontroller接口方法规范

1、	必须对接口方法操作进行说明：

```
@ApiOperation(value="获取学生列表", notes="分页获取学生列表")
```

2、	加上日志

```
protected static Logger logger= LoggerFactory.getLogger(StudentAPIController.class);
```

3、	请求路径中需标明请求方式，分为POST和GET：

```
@RequestMapping(value={"/getStudentList"},method= RequestMethod.GET)
```

4、	请求的接口方法带有参数时，必须对参数进行说明：

```
@ApiImplicitParams({
@ApiImplicitParam(name="pageNum",paramType = "query" ,value = "页码数"),
@ApiImplicitParam(name="家庭住址",paramType = "query" ,value = "家庭住址：模糊查询") 
})
```

5、	具体示例,详见StudentAPIController.java

####

异常处理规范

1、	Service层捕捉业务异常并抛出ServiceException
```
throw new ServiceException("数据保存失败",ErrorCode.SAVE_ERROR);
```

2、	Controller层捕捉数据及状态异常并返回APIResponse
```
if (null==formData) {
	throw new ServiceException("数据保存失败",ErrorCode.SAVE_ERROR); 
		}

```

####

界面代码编写规范，详见doc中的 [《XKES界面编写.pdf》](doc/XKES界面编写.pdf)

####

数据库开发规范

1、	使用powerdesigner进行模型设计，并对表和列进行注释

2、	属于系统框架的表以“XK_”开头，项目业务表以“项目英文名_”开头

3、	每个表中必须包含如下字段（详见数据库结构文档）
```
字段名	                   字段名	 	
删除状态	      del_flag    
创建人		  createuser
创建时间		  createtime
最后修改人		  lastchangeuser
最后修改时间	  lastchangetime
备注说明		  memo
数据状态		  datastatus
备用字段1		  f1
备用字段2		  f2
备用字段3		  f3
备用字段4		  f4
备用字段5		  f5

```
    








