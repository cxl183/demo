DROP TABLE IF EXISTS `demo_school`;

CREATE TABLE `demo_school` (
  `id` varchar(32) NOT NULL,
  `school_name` varchar(200) DEFAULT NULL,
  `school_desc` varchar(200) DEFAULT NULL,
  `school_code` varchar(200) DEFAULT NULL,
   del_flag             smallint comment '删除状态',
   createuser           varchar(100) comment '创建人',
   createtime           datetime comment '创建时间',
   lastchangeuser       varchar(100) comment '最后修改人',
   lastchangetime       datetime comment '最后修改时间',
   memo                 varchar(200) comment '备注说明',
   datastatus           smallint comment '数据状态',
   f1                   varchar(200) comment '备用字段1',
   f2                   varchar(200) comment '备用字段2',
   f3                   varchar(200) comment '备用字段3',
   f4                   varchar(200) comment '备用字段4',
   f5                   varchar(200) comment '备用字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `demo_classroom` */


DROP TABLE IF EXISTS `demo_classroom`;

CREATE TABLE `demo_classroom` (
  `id` varchar(32) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `grade` varchar(200) DEFAULT NULL,
   schoolid            varchar(32) comment '学校ID', 
   del_flag             smallint comment '删除状态',
   createuser           varchar(100) comment '创建人',
   createtime           datetime comment '创建时间',
   lastchangeuser       varchar(100) comment '最后修改人',
   lastchangetime       datetime comment '最后修改时间',
   memo                 varchar(200) comment '备注说明',
   datastatus           smallint comment '数据状态',
   f1                   varchar(200) comment '备用字段1',
   f2                   varchar(200) comment '备用字段2',
   f3                   varchar(200) comment '备用字段3',
   f4                   varchar(200) comment '备用字段4',
   f5                   varchar(200) comment '备用字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `demo_classroom` */



/*Table structure for table `demo_student` */

DROP TABLE IF EXISTS `demo_student`;

CREATE TABLE `demo_student` (
  `id` varchar(32) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `cid` varchar(32) DEFAULT NULL,
   del_flag             smallint comment '删除状态',
   createuser           varchar(100) comment '创建人',
   createtime           datetime comment '创建时间',
   lastchangeuser       varchar(100) comment '最后修改人',
   lastchangetime       datetime comment '最后修改时间',
   memo                 varchar(200) comment '备注说明',
   datastatus           smallint comment '数据状态',
   f1                   varchar(200) comment '备用字段1',
   f2                   varchar(200) comment '备用字段2',
   f3                   varchar(200) comment '备用字段3',
   f4                   varchar(200) comment '备用字段4',
   f5                   varchar(200) comment '备用字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `demo_student` */


/*
课程信息表
*/
CREATE TABLE `demo_course` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `course_code` varchar(32) NOT NULL COMMENT '课程编号',
  `course_name` varchar(255) DEFAULT NULL COMMENT '课程名称',
  `course_desc` varchar(1000) DEFAULT NULL COMMENT '课程介绍',
  del_flag             smallint comment '删除状态',
   createuser           varchar(100) comment '创建人',
   createtime           datetime comment '创建时间',
   lastchangeuser       varchar(100) comment '最后修改人',
   lastchangetime       datetime comment '最后修改时间',
   memo                 varchar(200) comment '备注说明',
   datastatus           smallint comment '数据状态',
   f1                   varchar(200) comment '备用字段1',
   f2                   varchar(200) comment '备用字段2',
   f3                   varchar(200) comment '备用字段3',
   f4                   varchar(200) comment '备用字段4',
   f5                   varchar(200) comment '备用字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
班级课程关联表
*/
CREATE TABLE `demo_class_course` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `class_id` varchar(32) NOT NULL COMMENT '班级ID',
  `course_id` varchar(255) DEFAULT NULL COMMENT '课程ID',
  del_flag             smallint comment '删除状态',
   createuser           varchar(100) comment '创建人',
   createtime           datetime comment '创建时间',
   lastchangeuser       varchar(100) comment '最后修改人',
   lastchangetime       datetime comment '最后修改时间',
   memo                 varchar(200) comment '备注说明',
   datastatus           smallint comment '数据状态',
   f1                   varchar(200) comment '备用字段1',
   f2                   varchar(200) comment '备用字段2',
   f3                   varchar(200) comment '备用字段3',
   f4                   varchar(200) comment '备用字段4',
   f5                   varchar(200) comment '备用字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
学生成绩表
*/
CREATE TABLE `demo_student_score` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `stu_id` varchar(32) NOT NULL COMMENT '学生ID',
  `course_id` varchar(32) DEFAULT NULL COMMENT '课程ID',
  `score` int(11) DEFAULT NULL COMMENT '分数',
   del_flag             smallint comment '删除状态',
   createuser           varchar(100) comment '创建人',
   createtime           datetime comment '创建时间',
   lastchangeuser       varchar(100) comment '最后修改人',
   lastchangetime       datetime comment '最后修改时间',
   memo                 varchar(200) comment '备注说明',
   datastatus           smallint comment '数据状态',
   f1                   varchar(200) comment '备用字段1',
   f2                   varchar(200) comment '备用字段2',
   f3                   varchar(200) comment '备用字段3',
   f4                   varchar(200) comment '备用字段4',
   f5                   varchar(200) comment '备用字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
