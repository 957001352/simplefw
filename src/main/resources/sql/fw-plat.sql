
/*==============================================================*/
/* Table: 设备保养项目                                              */
/*==============================================================*/
drop table if exists fw_devices_keep_item;
create table fw_devices_keep_item
(
   id                       integer                        NOT NULL AUTO_INCREMENT,
   name                     varchar(50)                    DEFAULT NULL COMMENT '保养项目名称',
   devices_classify         varchar(100)     			   DEFAULT NULL COMMENT '设备类型',
   content                  varchar(200)                   DEFAULT NULL COMMENT '保养内容描述',
   create_time              timestamp                      DEFAULT NOW() COMMENT '创建时间',
   deleted              	integer                        DEFAULT 0 COMMENT '删除状态 0正常 1删除',
   cycle              	    integer                        DEFAULT NULL COMMENT '保养周期 0日保养，1周保养，2月保养，3季度保养，4年保养',
   instructor              	varchar(100)                   DEFAULT NULL COMMENT '指导书附件',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备保养项目';


/*==============================================================*/
/* Table: 设备保养项目扩展                                          */
/*==============================================================*/
drop table if exists fw_keep_item_extension;
create table fw_keep_item_extension
(
   id                       integer                        NOT NULL AUTO_INCREMENT,
   keep_item_id             integer                        DEFAULT NULL COMMENT '保养项目',
   columns          		varchar(100)     			   DEFAULT NULL COMMENT '表头',
   rows                  	integer                  	   DEFAULT NULL COMMENT '行数',
   deleted              	integer                        DEFAULT 0 COMMENT '删除状态 0正常 1删除',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备保养项目扩展';


/*==============================================================*/
/* Table: 设备保养项目组                                          */
/*==============================================================*/
drop table if exists fw_devices_keep_team;
create table fw_devices_keep_team
(
   id                         integer                        NOT NULL AUTO_INCREMENT,
   name                       varchar(50)                    DEFAULT NULL COMMENT '保养项目组名称',
   devices_classify           varchar(100)     			     DEFAULT NULL COMMENT '设备类型',
   keep_item_ids		      varchar(100)				   	 DEFAULT NULL COMMENT '保养项目',
   product_devices_ids        varchar(100)                   DEFAULT NULL COMMENT '设备编号',
   create_time                timestamp                      DEFAULT NOW() COMMENT '创建时间',
   cycle              	      integer                        DEFAULT NULL COMMENT '保养周期',
   deleted              	  integer                        DEFAULT 0 COMMENT '删除状态 0正常 1删除',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备保养项目组';


/*==============================================================*/
/* Table: 设备保养计划制定                                      */
/*==============================================================*/
drop table if exists fw_devices_keep_plan;
create table fw_devices_keep_plan
(
   id                         integer                        NOT NULL AUTO_INCREMENT,
   name                       varchar(50)                    DEFAULT NULL COMMENT '保养计划名称',
   keep_team_id     	      integer    			   		 DEFAULT NULL COMMENT '设备保养项目组',
   product_devices_ids		  varchar(100)				   	 DEFAULT NULL COMMENT '设备编号',
   keep_cycle      			  integer                        DEFAULT NULL COMMENT '频率',
   start_time                 timestamp                      NULL COMMENT '初次保养时间',
   notice_time				  integer                        DEFAULT NULL COMMENT '提前通知时间',
   create_time                timestamp                      DEFAULT NOW() COMMENT '创建时间',
   last_time				  timestamp						 NULL COMMENT '上次保养时间',
   status				  	  integer						 DEFAULT 0 COMMENT '状态,0未下发,1已下发',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备保养计划制定';


/*==============================================================*/
/* Table: 设备保养计划下发任务                                  */
/*==============================================================*/
drop table if exists fw_devices_keep_task;
create table fw_devices_keep_task
(
   id                         integer                        NOT NULL AUTO_INCREMENT,
   execute_time               timestamp                      NULL COMMENT '执行时间',
   execute_user     	      integer    			   		 DEFAULT NULL COMMENT '执行人',
   status    	      		  varchar(50)    			   	 DEFAULT NULL COMMENT '任务状态',
   conclusion                 varchar(200)                   DEFAULT NULL COMMENT '保养结论',
   keep_plan_id     	      integer    			   		 DEFAULT NULL COMMENT '保养计划',
   non_execution		  	  varchar(100)				     DEFAULT NULL COMMENT '未执行原因',
   non_execut_cause			  varchar(200)				     DEFAULT NULL COMMENT '未执行原因描述',
   keep_order			      varchar(50)				     DEFAULT NULL COMMENT '保养工单号',
   create_time                timestamp                      DEFAULT NOW() COMMENT '任务下发时间',
   carry_day                  varchar(50)                    DEFAULT NULL COMMENT '延迟天数',
   form_code                  varchar(50)                    DEFAULT NULL COMMENT '表单code',
   note                       varchar(200)                   DEFAULT NULL COMMENT '备注',
   end_time                   timestamp                      NULL COMMENT '预计结束时间',
   delay_user                 varchar(50)                    DEFAULT NULL COMMENT '操作延后/关闭的人员',
   delay_time                 timestamp                      NULL COMMENT '操作延后/关闭的时间',
   start_time                 timestamp                      NULL COMMENT 'pad端开始时间',
   keep_photo                 varchar(100)                   DEFAULT NULL COMMENT 'pad端保养照片',
   keep_time                  timestamp                      NULL COMMENT '保养时间',
   devices_id                 varchar(10)                    DEFAULT NULL COMMENT '设备id',

   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备保养计划下发任务';


/*==============================================================*/
/* Table: 设备保养任务执行记录                                      */
/*==============================================================*/
drop table if exists fw_devices_plan_execute_record;
create table fw_devices_plan_execute_record
(
   id                         integer                        NOT NULL AUTO_INCREMENT,
   keep_task_id               integer                        DEFAULT NULL COMMENT '设备保养任务对象',
   keep_item_id     	      integer    			   		 DEFAULT NULL COMMENT '设备保养项目',
   keep_item_result    	      varchar(2000)  			   	 DEFAULT NULL COMMENT '设备保养项目结果',
   reason    	              varchar(100)  			   	 DEFAULT NULL COMMENT '不合格原因',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备保养任务执行记录';

/*==============================================================*/
/* Table: 设备维修项目                                              */
/*==============================================================*/
drop table if exists fw_devices_repair_item;
create table fw_devices_repair_item
(
   id                       integer                        NOT NULL AUTO_INCREMENT,
   name                     varchar(200)                   DEFAULT NULL COMMENT '维修方案项目',
   devices_classify         varchar(100)     			   DEFAULT NULL COMMENT '设备类型',
   create_time              timestamp                      DEFAULT NOW() COMMENT '创建时间',
   deleted                  integer                        DEFAULT 0 COMMENT '是否删除 默认0 1删除',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备维修项目';





/*==============================================================*/
/* Table: 维修项目                                              */
/*==============================================================*/
drop table if exists fw_basic_attachment;
CREATE TABLE fw_basic_attachment (
  id            integer NOT NULL AUTO_INCREMENT,
  name          varchar(100) DEFAULT NULL,
  save_name     varchar(50) DEFAULT NULL,
  path          varchar(100) DEFAULT NULL,
  suffix        varchar(10) DEFAULT NULL,
  create_time   timestamp                      DEFAULT NOW() COMMENT '创建时间',
  data_id       varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公共附件表';

/*==============================================================*/
/* Table: 设备维修                                            */
/*==============================================================*/
drop table if exists fw_devices_repair;
create table fw_devices_repair
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   code                     varchar(20)                   	DEFAULT NULL COMMENT '维修方案编码',
   product_devices_id       varchar(100)     			  	DEFAULT NULL COMMENT '设备维修编号',
   devices_classify       	varchar(100)     			  	DEFAULT NULL COMMENT '设备类型',
   question                 varchar(100)                  	DEFAULT NULL COMMENT '问题',
   question_desc            varchar(500)					DEFAULT NULL COMMENT '问题描述',
   priority					varchar(20)						DEFAULT NULL COMMENT '优先级  0普通 1优先',
   before_file				varchar(200)					DEFAULT NULL COMMENT '维修前照片',
   create_user				integer							DEFAULT NULL COMMENT '申请人',
   create_time				timestamp						DEFAULT now() COMMENT '申请时间',
   repair_team_ids			varchar(100)					DEFAULT NULL COMMENT '设备维修项目',
   add_plan					varchar(200)					DEFAULT NULL COMMENT '方案补充',
   plan_file				varchar(100)					DEFAULT NULL COMMENT '方案补充附件',
   plan_hour				decimal(5,2)					DEFAULT NULL COMMENT '预计维修耗时',
   is_review				integer							DEFAULT NULL COMMENT '维修后是否评审 0未评审 1已评审',
   project_create_user		integer							DEFAULT NULL COMMENT '方案制定人',
   project_create_time		timestamp						NULL DEFAULT NULL COMMENT '方案制定时间',
   after_file				varchar(200)					DEFAULT NULL COMMENT '维修后照片',
   source					integer							DEFAULT NULL COMMENT '来源0PC端 1PAD端',
   status					integer							DEFAULT NULL COMMENT '维修结果',
   result					varchar(500)					DEFAULT NULL COMMENT '维修结果描述',
   repair_user				integer							DEFAULT NULL COMMENT '维修人员',
   repair_time				timestamp						NULL DEFAULT NULL COMMENT '维修时间',
   start_time				timestamp						NULL DEFAULT NULL COMMENT '维修开始时间',
   finish_time				timestamp						NULL DEFAULT NULL COMMENT '维修结束时间',
   repair_project_status	integer							DEFAULT 0 COMMENT '维修方案制定状态 0未制定 1已制定',
   repair_task__exe_status	integer							DEFAULT 0 COMMENT '维修任务执行状态 0未执行 1已执行',
   task_received            integer                         DEFAULT 0 COMMENT '任务是否已接收 默认0 0未接收 1已接收',
   note						varchar(500)					DEFAULT NULL COMMENT '备注',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备维修单';

/*==============================================================*/
/* Table: 设备问题列表                                            */
/*==============================================================*/
drop table if exists fw_devices_problem;
create table fw_devices_problem
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   name        				varchar(50)                  	DEFAULT NULL COMMENT '问题名称',
   content       			varchar(200)    			  	DEFAULT NULL COMMENT '问题描述',
   status       			integer     			  		DEFAULT NULL COMMENT '状态',
   type 					integer                  		DEFAULT NULL COMMENT '问题类型',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备问题列表';


/*==============================================================*/
/* Table: 设备点检项目                                              */
/*==============================================================*/
drop table if exists fw_devices_check_item;
create table fw_devices_check_item
(
   id                       integer                        NOT NULL AUTO_INCREMENT,
   name                     varchar(50)                    DEFAULT NULL COMMENT '项目名称',
   devices_classify         varchar(100)     			   DEFAULT NULL COMMENT '设备类型',
   content                  varchar(200)                   DEFAULT NULL COMMENT '内容描述',
   create_time              timestamp                      DEFAULT NOW() COMMENT '创建时间',
   deleted                  integer                        DEFAULT 0 COMMENT '删除状态',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备点检项目';
/*==============================================================*/
/* Table: 设备点检项目组                                            */
/*==============================================================*/
drop table if exists fw_devices_check_team;
create table fw_devices_check_team
(
   id                         integer                        NOT NULL AUTO_INCREMENT,
   name                       varchar(50)                    DEFAULT NULL COMMENT '项目组名称',
   devices_classify           varchar(100)     			     DEFAULT NULL COMMENT '设备类型',
   check_item_ids		      varchar(100)				   	 DEFAULT NULL COMMENT '点检项目',
   product_devices_ids        varchar(100)                   DEFAULT NULL COMMENT '设备编号',
   create_time                timestamp                      DEFAULT NOW() COMMENT '创建时间',
   deleted                    integer                        DEFAULT 0 COMMENT '删除状态',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备点检项目组';

/*==============================================================*/
/* Table: 设备点检                                            */
/*==============================================================*/
drop table if exists fw_devices_check;
create table fw_devices_check
(
   id                         integer                        NOT NULL AUTO_INCREMENT,
   code                       varchar(50)                    DEFAULT NULL COMMENT '项目组名称',
   product_code               varchar(100)     			     DEFAULT NULL COMMENT '生产指令',
   product_devices_id		  integer				   		 DEFAULT NULL COMMENT '设备编号',
   check_team_ids             varchar(100)                   DEFAULT NULL COMMENT '点检项目组',
   check_file                 varchar(100)                   DEFAULT NULL COMMENT '点检照片',
   execute_time               timestamp                      NULL COMMENT '执行时间',
   execute_user               integer                        DEFAULT NULL COMMENT '执行人',
   status                     integer                        DEFAULT 0 COMMENT '执行状态',
   create_time                timestamp                      DEFAULT NOW() COMMENT '创建时间',
   note                       varchar(200)                   DEFAULT NULL COMMENT '备注',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备点检';

/*==============================================================*/
/* Table: 设备点检结论                                      */
/*==============================================================*/
drop table if exists fw_devices_check_detail;
create table fw_devices_check_detail
(
   id                         integer                        NOT NULL AUTO_INCREMENT,
   check_record_id            integer                        DEFAULT NULL COMMENT '设备点检',
   check_item_id     	      integer    			   		 DEFAULT NULL COMMENT '点检项目',
   check_item_result    	  integer  			   	         DEFAULT NULL COMMENT '点检结果',
   reason    	              varchar(100)  			   	 DEFAULT NULL COMMENT '不合格原因',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备点检结论';
/*==============================================================*/
/* Table: 设备维修备品备件                                            */
/*==============================================================*/
drop table if exists fw_devices_repair_spare;
create table fw_devices_repair_spare
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   devices_repair_id        integer                  		DEFAULT NULL COMMENT '设备维修ID',
   repair_plan       		integer    			  			DEFAULT NULL COMMENT '修改方案 0维修 1更换',
   use_count       			integer     			  		DEFAULT NULL COMMENT '使用数量',
   devices_spare_id         integer                  		DEFAULT NULL COMMENT '备品备件id',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备维修备品备件';

/*==============================================================*/
/* Table: 模具维修项目                                              */
/*==============================================================*/
drop table if exists fw_mould_repair_item;
create table fw_mould_repair_item
(
   id                       integer                        NOT NULL AUTO_INCREMENT,
   name                     varchar(200)                   DEFAULT NULL COMMENT '模具维修方案项目',
   create_time              timestamp                      DEFAULT NOW() COMMENT '创建时间',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模具维修项目';


/*==============================================================*/
/* Table: 审核流程定义                                            */
/*==============================================================*/
drop table if exists fw_jbpm_deployment;
create table fw_jbpm_deployment
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   form_id              integer                        DEFAULT NULL COMMENT '审核表单',
   name                 varchar(100)                   DEFAULT NULL COMMENT '流程名称',
   create_time          timestamp                      DEFAULT NOW() COMMENT '添加时间',
   create_user          integer                        DEFAULT NULL COMMENT '添加人',
   update_time          timestamp                      NULL COMMENT '修改时间',
   update_user          integer                        DEFAULT NULL COMMENT '修改人',
   mess_notice          integer                        DEFAULT NULL COMMENT '页面通知',
   email_notice         integer                        DEFAULT NULL COMMENT '邮件通知',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审核流程定义';



/*==============================================================*/
/* Table: 审核流程节点定义                                      */
/*==============================================================*/
drop table if exists fw_jbpm_deployprop;
create table fw_jbpm_deployprop
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   deployment_id        integer                        DEFAULT NULL COMMENT '审核流程对象',
   audit_users          varchar(100)                   DEFAULT NULL COMMENT '审核人',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审核流程定义';

/*==============================================================*/
/* Table: 审核表单对象                                      */
/*==============================================================*/
drop table if exists fw_jbpm_form;
create table fw_jbpm_form
(
   id                   integer                       NOT NULL AUTO_INCREMENT,
   code                 varchar(50)                   DEFAULT NULL COMMENT '表单编码',
   name                 varchar(50)                   DEFAULT NULL COMMENT '表单名称',
   url                  varchar(50)                   DEFAULT NULL COMMENT '表单地址',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审核表单对象';
/*==============================================================*/
/* Table: 流程执行实例                                     */
/*==============================================================*/
drop table if exists fw_jbpm_execution;
create table fw_jbpm_execution
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   deployment_id        integer                        DEFAULT NULL COMMENT '审核流程对象',
   jbpm_no              varchar(100)                   DEFAULT NULL COMMENT '审核单号',
   data_id              varchar(100)                   DEFAULT NULL COMMENT '审核数据',
   create_time          timestamp                      DEFAULT NOW() COMMENT '添加时间',
   create_user          integer                        DEFAULT NULL COMMENT '添加人',
   audit_status         varchar(20)                    DEFAULT NULL COMMENT '审核状态  审核完成时为xxx审核通过，未完成时为待xxx审核',
   end_time             timestamp                      NULL COMMENT '审核完成时间',
   audit_result         integer                        DEFAULT 0 COMMENT '审核结论',
   form_code            varchar(50)                    DEFAULT NULL COMMENT '表单编码',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程执行实例';
/*==============================================================*/
/* Table: 待审核记录                                          */
/*==============================================================*/
drop table if exists fw_jbpm_task;
create table fw_jbpm_task
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   execution_id         integer                        DEFAULT NULL COMMENT '流程实例',
   deployment_id        integer                        DEFAULT NULL COMMENT '待审核流程',
   deployprop_id        integer                        DEFAULT NULL COMMENT '待审核节点',
   audit_user           varchar(100)                   DEFAULT NULL COMMENT '待审核人',
   data_id              varchar(100)                   DEFAULT NULL COMMENT '数据对象Id',
   create_time          timestamp                      DEFAULT NOW() COMMENT '任务下发时间',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='待审核记录';
/*==============================================================*/
/* Table: 已审核记录                                     */
/*==============================================================*/
drop table if exists fw_jbpm_hist_task;
create table fw_jbpm_hist_task
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   execution_id         integer                        DEFAULT NULL COMMENT '流程实例',
   deployment_id        integer                        DEFAULT NULL COMMENT '审核流程',
   audit_node           varchar(100)                   DEFAULT NULL COMMENT '审核节点 待xxx审核',
   audit_idea           varchar(200)                   DEFAULT NULL COMMENT '审核意见',
   audit_user           integer                        DEFAULT NULL COMMENT '审核人',
   audit_result         integer                        DEFAULT NULL COMMENT '审核结论',
   audit_time           timestamp                      DEFAULT NOW() COMMENT '审核时间',
   create_time          timestamp                      NULL COMMENT '待审核任务下发时间',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='已审核记录';

/*==============================================================*/
/* Table: 备品备件                                              */
/*==============================================================*/
drop table if exists fw_product_devices_spare;
create table fw_product_devices_spare
(
    id                  integer     	           NOT NULL AUTO_INCREMENT,
    code        		varchar(100)	           DEFAULT NULL COMMENT '物料代码',
    name       			varchar(100)	           DEFAULT NULL COMMENT '物料名称',
    unit         		varchar(100)	           DEFAULT NULL COMMENT '单位',
    max_store 			integer     	           DEFAULT NULL COMMENT '最大安全库存',
    min_store 			integer     	           DEFAULT NULL COMMENT '最小安全库存',
    store_count			integer						DEFAULT NULL COMMENT '库存数量',
    create_user   		integer     	           DEFAULT NULL COMMENT '添加人',
    create_time  		timestamp   	           DEFAULT NOW() COMMENT '添加时间',
    update_user  		integer     	           DEFAULT NULL COMMENT '修改人',
    update_time  		timestamp   	           DEFAULT NOW() COMMENT '修改时间',
    location      		varchar(50) 	           DEFAULT NULL COMMENT '库位',
    primary key  (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='备品备件';




/*==============================================================*/
/* Table: 出入库明细                                   		    */
/*==============================================================*/
drop table if exists fw_devices_outin_detail;
create table fw_devices_outin_detail
(
    id                   integer                    NOT NULL AUTO_INCREMENT,
    devices_spare_id 	integer                    DEFAULT NULL COMMENT '备品备件',
    use_count    		integer                    DEFAULT NULL COMMENT '数量',
    location      		varchar(50)                DEFAULT NULL COMMENT '库位',
    operate       		integer                    DEFAULT NULL COMMENT '操作',
    out_in      			integer                    DEFAULT NULL COMMENT '出入库对象',
    primary key  (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出入库明细';



/*==============================================================*/
/* Table: 备品备件出库                                          */
/*==============================================================*/
drop table if exists fw_devices_spare_out;
create table fw_devices_spare_out
(
    id                   integer                    NOT NULL AUTO_INCREMENT,
    out_no      			varchar(100)               DEFAULT NULL COMMENT '出库单号',
    order_no    			varchar(100)               DEFAULT NULL COMMENT '关联订单号',
    create_user   		integer                    DEFAULT NULL COMMENT '制单人',
    create_time  		timestamp                  DEFAULT NOW() COMMENT '制单时间',
    note           		varchar(200)               DEFAULT NULL COMMENT '备注',
    primary key  (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='备品备件出库';




/*==============================================================*/
/* Table: 备品备件入库                                          */
/*==============================================================*/
drop table if exists fw_devices_spare_in;
create table fw_devices_spare_in
(
    id                   integer                    NOT NULL AUTO_INCREMENT,
    in_no      			varchar(100)               DEFAULT NULL COMMENT '入库单号',
    order_no    			varchar(100)               DEFAULT NULL COMMENT '关联订单号',
    create_user   		integer                    DEFAULT NULL COMMENT '制单人',
    create_time  		timestamp                  DEFAULT NOW() COMMENT '制单时间',
    note           		varchar(200)               DEFAULT NULL COMMENT '备注',
    primary key  (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='备品备件入库';





/*==============================================================*/
/* Table: 模具维修备品备件                                            */
/*==============================================================*/
drop table if exists fw_mould_repair_spare;
create table fw_mould_repair_spare
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   mould_repair_id          integer                         DEFAULT NULL COMMENT '模具维修ID',
   repair_plan       		integer    			  			DEFAULT NULL COMMENT '修改方案 0维修 1更换',
   use_count       			integer     			  		DEFAULT NULL COMMENT '使用数量',
   mould_spare_id           integer                  		DEFAULT NULL COMMENT '备品备件id',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模具维修备品备件';

/*==============================================================*/
/* Table: 模具维修                                            */
/*==============================================================*/
drop table if exists fw_mould_repair;
create table fw_mould_repair
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   code                     varchar(20)                   	DEFAULT NULL COMMENT '模具维修方案编码',
   mould_id                 varchar(100)     			  	DEFAULT NULL COMMENT '模具编号',
   mould_name               varchar(100)     			  	DEFAULT NULL COMMENT '模具名称',
   question                 varchar(100)                  	DEFAULT NULL COMMENT '问题',
   question_desc            varchar(500)					DEFAULT NULL COMMENT '问题描述',
   priority					varchar(20)						DEFAULT NULL COMMENT '优先级  0普通 1优先',
   before_file				varchar(200)					DEFAULT NULL COMMENT '维修前照片',
   create_user				integer							DEFAULT NULL COMMENT '申请人',
   create_time				timestamp						DEFAULT now() COMMENT '申请时间',
   repair_team_ids			varchar(100)					DEFAULT NULL COMMENT '设备维修项目',
   add_plan					varchar(200)					DEFAULT NULL COMMENT '方案补充',
   plan_file				varchar(100)					DEFAULT NULL COMMENT '方案补充附件',
   plan_hour				decimal(5,2)					DEFAULT NULL COMMENT '预计维修耗时',
   is_review				integer							DEFAULT NULL COMMENT '维修后是否评审 0未评审 1已评审',
   project_create_user		integer							DEFAULT NULL COMMENT '方案制定人',
   project_create_time		timestamp						NULL DEFAULT NULL COMMENT '方案制定时间',
   after_file				varchar(200)					DEFAULT NULL COMMENT '维修后照片',
   source					integer							DEFAULT NULL COMMENT '来源0PC端 1PAD端',
   status					integer							DEFAULT 0  COMMENT '维修结果 0未完成 1完成',
   result					varchar(500)					DEFAULT NULL COMMENT '维修结果描述',
   repair_user				integer							DEFAULT NULL COMMENT '维修人员',
   repair_time				timestamp						NULL  COMMENT '维修时间',
   start_time				timestamp						NULL  COMMENT '维修开始时间',
   finish_time				timestamp						NULL  COMMENT '维修结束时间',
   repair_project_status	integer							DEFAULT 0 COMMENT '维修方案制定状态 0未制定 1已制定',
   repair_task__exe_status	integer							DEFAULT 0 COMMENT '维修任务执行状态 0未执行 1已执行',
   task_received            integer                         DEFAULT 0 COMMENT '任务是否已接收 默认0 0未接收 1已接收',
   cavity_number            integer                         DEFAULT 0 COMMENT '任务是否已接收 默认0 0未接收 1已接收',
   note						varchar(500)					DEFAULT NULL COMMENT '备注',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备维修单';

/*==============================================================*/
/* Table: 设备履历                                            */
/*==============================================================*/
drop table if exists fw_product_devices_extension;
create table fw_product_devices_extension
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   product_devices_id       varchar(50)                  	DEFAULT NULL COMMENT 'E2C设备id',
   shzcd                    double     			  	        DEFAULT NULL COMMENT '射嘴长度',
   shzwj                    double    			  	        DEFAULT NULL COMMENT '射嘴外径',
   shchrl                   double                  	    DEFAULT NULL COMMENT '射出容量',
   shechzhl                 double					        DEFAULT NULL COMMENT '射出重量',
   lgzhj					double						    DEFAULT NULL COMMENT '螺杆直径',
   lgshnl				    double					        DEFAULT NULL COMMENT '螺杆塑化能力',
   shchyl				    double						    DEFAULT NULL COMMENT '射出压力',
   dchl				        double						    DEFAULT NULL COMMENT '顶出力',
   dgshl			        double					        DEFAULT NULL COMMENT '顶杆数量',
   dgkzhj					double					        DEFAULT NULL COMMENT '顶杆空直径',
   dzhxch				    double					        DEFAULT NULL COMMENT '顶针行程',
   mjzdbhhd				    double					        DEFAULT NULL COMMENT '模具最大闭合厚度',
   zdkmxch				    double							DEFAULT NULL COMMENT '最大开模行程',
   dzhjj_hv		            varchar(50)						DEFAULT NULL COMMENT '导柱间距(HV)',
   zxmjchc		            double						    DEFAULT NULL COMMENT '最小模具尺寸',
   yyzhz				    double					        DEFAULT NULL COMMENT '油压中子',
   qyzhz					double							DEFAULT NULL COMMENT '气压中子',
   dwhzhj					double							DEFAULT NULL COMMENT '定位环直径',
   shzqmbj					double					        DEFAULT NULL COMMENT '射嘴球面半径',
   shzjchyl				    double							DEFAULT NULL COMMENT '射嘴接触压力',
   shbchc				    varchar(50)						DEFAULT NULL COMMENT '设备尺寸',
   shbzhl				    double						    DEFAULT NULL COMMENT '设备重量',
   yyll				        double						    DEFAULT NULL COMMENT '液压流量',
   djgl	                    double							DEFAULT NULL COMMENT '电机功率',
   jrgl	                    double							DEFAULT NULL COMMENT '加热功率',
   jxsh						double					        DEFAULT NULL COMMENT '机械手',
   into_time				timestamp 					    DEFAULT now() COMMENT '入场时间',
   status					integer					        DEFAULT 0 COMMENT '设备状态 0:正常 1:停用 2:报废',

   shzkj				    double 					        DEFAULT NULL COMMENT '射咀孔径',
   mbchc					double					        DEFAULT NULL COMMENT '模板尺寸',

   extend_attr				varchar(1000)					DEFAULT NULL COMMENT '自定义属性',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备履历';

/*==============================================================*/
/* Table: 设备报废记录                                           */
/*==============================================================*/
drop table if exists fw_product_devices_scrap;
create table fw_product_devices_scrap
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   devices_extension_id     integer                   	    DEFAULT NULL COMMENT '设备履历',
   creat_user               integer     			  	    DEFAULT NULL COMMENT '申请人',
   create_time              timestamp    			  	    DEFAULT now() COMMENT '申请时间',
   reason                   varchar(200)                  	DEFAULT NULL COMMENT '报废原因',
   data_id                  varchar(200)                  	DEFAULT NULL COMMENT '附件id',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备报废记录';

/*==============================================================*/
/* Table: 模具仓库管理                                           */
/*==============================================================*/
drop table if exists fw_mould_storage_house;
create table fw_mould_storage_house
(
   id                   integer                        	NOT NULL AUTO_INCREMENT,
   name                 varchar(50)                     DEFAULT NULL COMMENT '仓库',
   status               integer                         DEFAULT 0    COMMENT '状态 0 正常 1删除 3-待出库 4-已出库 5-待入库 6-已入库 7-待移库',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模具仓库管理';
/*==============================================================*/
/* Table: 模具库位管理                                           */
/*==============================================================*/
drop table if exists fw_mould_storage_location;
create table fw_mould_storage_location
(
   id                   integer                        	NOT NULL AUTO_INCREMENT,
   name                 varchar(50)                     DEFAULT NULL COMMENT '库位',
   status               integer                         DEFAULT 0    COMMENT '状态 0 正常 1删除',
   storage_house_id     integer                         DEFAULT NULL COMMENT '仓库',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模具库位管理';

/*==============================================================*/
/* Table: 模具入库、出库、移库                                   */
/*==============================================================*/
drop table if exists fw_mould_house;
create table fw_mould_house
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   now_location_id      integer                        DEFAULT NULL COMMENT '库位对象',
   mould_id             integer                        DEFAULT NULL COMMENT '模具',
   house_no             varchar(50)                    DEFAULT NULL COMMENT '工单号',
   create_user          integer                        DEFAULT NULL COMMENT '操作人',
   create_time          timestamp                      DEFAULT now() COMMENT '操作时间',
   execute_time         timestamp                      NULL COMMENT '执行时间',
   execute_user         integer                        DEFAULT NULL COMMENT '执行人',
   operate              integer                        DEFAULT NULL COMMENT '操作类型 0 入库 1出库 2移库',
   status               integer                        DEFAULT 0 COMMENT '状态 0 未执行 1已执行',
   old_location_id      integer                        DEFAULT NULL COMMENT '旧库位对象 移库填入旧库位对象，出入库为空',
   injection_molding_id integer                        DEFAULT NULL COMMENT '生产指令id',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模具入库、出库、移库';

/*==============================================================*/
/* Table: 模具                                     */
/*==============================================================*/
drop table if exists fw_mould_devices;
create table fw_mould_devices
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   code                 varchar(50)                    DEFAULT NULL COMMENT '模具编号',
   name                 varchar(50)                    DEFAULT NULL COMMENT '模具名称',
   life_time            integer                        DEFAULT NULL COMMENT '模具寿命',
   status               integer                        DEFAULT 0 COMMENT '使用状态 0-正常，1-使用中，2-EOP  8-报废',
   create_time          timestamp                      DEFAULT NOW() COMMENT '添加时间',
   use_time             timestamp                      NULL COMMENT '入厂时间',
   now_location_id      integer                        DEFAULT 0 COMMENT '当前库位对象',

   turn_status          integer                        DEFAULT NULL COMMENT '阶段状态 0-试制阶段 1-量产阶段 2-项目EOP 3-报废',
   molding              varchar(50)                    DEFAULT NULL COMMENT '成型周期',
   cavity               varchar(50)                    DEFAULT NULL COMMENT '型腔数',
   material             varchar(50)                    DEFAULT NULL COMMENT '物料长代码',
   init_count           integer                        DEFAULT 0 COMMENT '初始开始模次数',
   parent_id            varchar(200)                   DEFAULT NULL COMMENT '关联模具id',
   old_location_id      integer                        DEFAULT NULL COMMENT '旧库位对象 默认为空，解绑后将当前库位对象写入旧库位，并清空当前库位对象，绑定后清空旧库位对象',

   weight               integer                        DEFAULT NULL COMMENT '重量',
   sizes                varchar(50)                    DEFAULT NULL COMMENT '尺寸(长*宽*高)',
   vender               varchar(50)                    DEFAULT NULL COMMENT '模具厂家',
   client_name          varchar(50)                    DEFAULT NULL COMMENT '客户名称',
   product              varchar(50)                    DEFAULT NULL COMMENT '产品',
   data_id              varchar(100)                   DEFAULT NULL COMMENT '照片',
   type                 integer                        DEFAULT NULL COMMENT '模具类型 0主体 1镶体 2嵌体',

   extend_attr			varchar(1000)				   DEFAULT NULL COMMENT '自定义属性',
   total_count			integer				           DEFAULT 0 COMMENT '累计开合模次数',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模具';

/*==============================================================*/
/* Table: 模具停用                                     */
/*==============================================================*/
drop table if exists fw_mould_devices_stop;
create table fw_mould_devices_stop
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   mould_devices_id     integer                        DEFAULT NULL COMMENT '模具履历',
   creat_user           integer                        DEFAULT NULL COMMENT '申请人',
   create_time          timestamp                      DEFAULT now() COMMENT '申请时间',
   reason               varchar(200)                   DEFAULT NULL COMMENT '停用原因',
   data_id              varchar(100)                   DEFAULT NULL COMMENT '附件id',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模具停用';

/*==============================================================*/
/* Table: 模具转段                                    */
/*==============================================================*/
drop table if exists fw_mould_devices_turn;
create table fw_mould_devices_turn
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   mould_devices_id     integer                        DEFAULT NULL COMMENT '模具履历',
   creat_user           integer                        DEFAULT NULL COMMENT '申请人',
   create_time          timestamp                      DEFAULT now() COMMENT '申请时间',
   reason               varchar(200)                   DEFAULT NULL COMMENT '转段原因',
   data_id              varchar(100)                   DEFAULT NULL COMMENT '附件id',
   stage                integer                        DEFAULT NULL COMMENT '阶段',
   pre_status           integer                        DEFAULT NULL COMMENT '上个阶段',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模具转段';

/*==============================================================*/
/* Table: 模具报废                                    */
/*==============================================================*/
drop table if exists fw_mould_devices_scrap;
create table fw_mould_devices_scrap
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   mould_devices_id     integer                        DEFAULT NULL COMMENT '模具履历',
   creat_user           integer                        DEFAULT NULL COMMENT '申请人',
   create_time          timestamp                      DEFAULT now() COMMENT '申请时间',
   reason               varchar(200)                   DEFAULT NULL COMMENT '转段原因',
   data_id              varchar(100)                   DEFAULT NULL COMMENT '附件id',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模具报废';

/*==============================================================*/
/* Table: 模具备品备件                                              */
/*==============================================================*/
drop table if exists fw_mould_devices_spare;
create table fw_mould_devices_spare
(
   id                   integer     	           NOT NULL AUTO_INCREMENT,
   code        			varchar(100)	           DEFAULT NULL COMMENT '物料代码',
   name       			varchar(100)	           DEFAULT NULL COMMENT '物料名称',
   unit         		varchar(100)	           DEFAULT NULL COMMENT '单位',
   max_store 			integer     	           DEFAULT NULL COMMENT '最大安全库存',
   min_store 			integer     	           DEFAULT NULL COMMENT '最小安全库存',
   store_count			integer						DEFAULT NULL COMMENT '库存数量',
   create_user   		integer     	           DEFAULT NULL COMMENT '添加人',
   create_time  		timestamp   	           DEFAULT NOW() COMMENT '添加时间',
   update_user  		integer     	           DEFAULT NULL COMMENT '修改人',
   update_time  		timestamp   	           NULL COMMENT '修改时间',
   location      		varchar(50) 	           DEFAULT NULL COMMENT '库位',
   primary key  (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模具备品备件';

/*==============================================================*/
/* Table: 出入库明细                                   		    */
/*==============================================================*/
drop table if exists fw_mould_outin_detail;
create table fw_mould_outin_detail
(
   id                   integer                    NOT NULL AUTO_INCREMENT,
   devices_spare_id 	integer                    DEFAULT NULL COMMENT '备品备件',
   use_count    		integer                    DEFAULT NULL COMMENT '数量',
   location      		varchar(50)                DEFAULT NULL COMMENT '库位',
   operate       		integer                    DEFAULT NULL COMMENT '操作 0入库 1出库',
   out_in      			integer                    DEFAULT NULL COMMENT '出入库对象',
   primary key  (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模具出入库明细';

/*==============================================================*/
/* Table: 备品备件出库                                          */
/*==============================================================*/
drop table if exists fw_mould_spare_out;
create table fw_mould_spare_out
(
   id                   integer                    NOT NULL AUTO_INCREMENT,
   out_no      			varchar(100)               DEFAULT NULL COMMENT '出库单号',
   order_no    			varchar(100)               DEFAULT NULL COMMENT '关联订单号',
   create_user   		integer                    DEFAULT NULL COMMENT '制单人',
   create_time  		timestamp                  DEFAULT NOW() COMMENT '制单时间',
   note           		varchar(200)               DEFAULT NULL COMMENT '备注',
   primary key  (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模具备品备件出库';

/*==============================================================*/
/* Table: 备品备件入库                                          */
/*==============================================================*/
drop table if exists fw_mould_spare_in;
create table fw_mould_spare_in
(
   id                   integer                    NOT NULL AUTO_INCREMENT,
   in_no      			varchar(100)               DEFAULT NULL COMMENT '入库单号',
   order_no    			varchar(100)               DEFAULT NULL COMMENT '关联订单号',
   create_user   		integer                    DEFAULT NULL COMMENT '制单人',
   create_time  		timestamp                  DEFAULT NOW() COMMENT '制单时间',
   note           		varchar(200)               DEFAULT NULL COMMENT '备注',
   primary key  (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模具备品备件入库';

/*==============================================================*/
/* Table: 模具使用                                          */
/*==============================================================*/
drop table if exists fw_mould_use_record;
create table fw_mould_use_record
(
   id                   integer                    NOT NULL AUTO_INCREMENT,
   product_devices_id   integer                    DEFAULT NULL COMMENT '设备',
   mould_devices_id    	integer                    DEFAULT NULL COMMENT '模具',
   product_code   		varchar(50)                DEFAULT NULL COMMENT '生产指令',
   create_time  		timestamp                  DEFAULT NOW() COMMENT '创建时间',
   opreate           	integer                    DEFAULT NULL COMMENT '操作 0 上模 1下模',
   end_file           	varchar(200)               DEFAULT NULL COMMENT '尾模照片',
   status           	integer                    DEFAULT NULL COMMENT '状态 0 未完成 1 已完成',
   task_status          integer                    DEFAULT NULL COMMENT '任务状态 0 待上下模 1 上下模完成',
   create_user          integer                    DEFAULT NULL COMMENT '创建人',
   injection_molding_id integer                    DEFAULT NULL COMMENT '生产指令id',
   primary key  (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模具使用';


/*==============================================================*/
/* Table: 模具保养项目                                              */
/*==============================================================*/
drop table if exists fw_mould_keep_item;
create table fw_mould_keep_item
(
   id                       integer                        NOT NULL AUTO_INCREMENT,
   name                     varchar(50)                    DEFAULT NULL COMMENT '保养项目名称',
   content                  varchar(200)                   DEFAULT NULL COMMENT '保养内容描述',
   create_time              timestamp                      DEFAULT NOW() COMMENT '创建时间',
   deleted              	integer                        DEFAULT 0 COMMENT '删除状态 0正常 1删除',
   cycle              	    integer                        DEFAULT NULL COMMENT '保养周期 0日保养，1周保养，2月保养，3季度保养，4年保养',
   instructor              	varchar(100)                   DEFAULT NULL COMMENT '指导书附件',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模具保养项目';


/*==============================================================*/
/* Table: 模具保养项目组                                            */
/*==============================================================*/
drop table if exists fw_mould_keep_team;
create table fw_mould_keep_team
(
   id                         integer                        NOT NULL AUTO_INCREMENT,
   name                       varchar(50)                    DEFAULT NULL COMMENT '保养项目组名称',
   keep_item_ids		      varchar(100)				   	 DEFAULT NULL COMMENT '保养项目',
   mould_devices_ids          varchar(100)                   DEFAULT NULL COMMENT '模具编号',
   create_time                timestamp                      DEFAULT NOW() COMMENT '创建时间',
   deleted              	  integer                        DEFAULT 0 COMMENT '删除状态 0正常 1删除',
   cycle              	      integer                        DEFAULT NULL COMMENT '保养周期',
   keep_type              	  integer                        DEFAULT NULL COMMENT '保养类型',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模具保养项目组';


/*==============================================================*/
/* Table: 模具保养计划制定                                      */
/*==============================================================*/
drop table if exists fw_mould_keep_plan;
create table fw_mould_keep_plan
(
   id                         integer                        NOT NULL AUTO_INCREMENT,
   name                       varchar(50)                    DEFAULT NULL COMMENT '保养计划名称',
   keep_team_id     	      integer    			   		 DEFAULT NULL COMMENT '设备保养项目组',
   mould_devices_ids		  varchar(100)				   	 DEFAULT NULL COMMENT '模具编号',
   keep_cycle      			  integer                        DEFAULT NULL COMMENT '频率',
   start_time                 timestamp                      NULL COMMENT '初次保养时间',
   notice_time				  integer                        DEFAULT NULL COMMENT '提前通知时间',
   create_time                timestamp                      DEFAULT NOW() COMMENT '创建时间',
   last_time				  timestamp						 NULL COMMENT '上次保养时间',
   status				  	  integer						 DEFAULT 0 COMMENT '状态,0未下发,1已下发',
   keep_fre				  	  integer						 DEFAULT NULL COMMENT '保养周期',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模具保养计划制定';


/*==============================================================*/
/* Table: 模具保养计划下发任务                                  */
/*==============================================================*/
drop table if exists fw_mould_keep_task;
create table fw_mould_keep_task
(
   id                         integer                        NOT NULL AUTO_INCREMENT,
   execute_time               timestamp                      NULL COMMENT '执行时间',
   execute_user     	      integer    			   		 DEFAULT NULL COMMENT '执行人',
   status    	      		  varchar(50)    			   	 DEFAULT NULL COMMENT '任务状态',
   conclusion                 varchar(200)                   DEFAULT NULL COMMENT '保养结论',
   keep_plan_id     	      integer    			   		 DEFAULT NULL COMMENT '保养计划',
   non_execution		  	  varchar(100)				     DEFAULT NULL COMMENT '未执行原因',
   non_execut_cause			  varchar(200)				     DEFAULT NULL COMMENT '未执行原因描述',
   keep_order			      varchar(50)				     DEFAULT NULL COMMENT '保养工单号',
   create_time                timestamp                      DEFAULT NOW() COMMENT '任务下发时间',
   carry_day                  varchar(50)                    DEFAULT NULL COMMENT '延迟天数',
   form_code                  varchar(50)                    DEFAULT NULL COMMENT '表单code',
   note                       varchar(200)                   DEFAULT NULL COMMENT '备注',
   end_time                   timestamp                      NULL COMMENT 'pad端结束时间',
   delay_user                 varchar(50)                    DEFAULT NULL COMMENT '操作延后/关闭的人员',
   delay_time                 timestamp                      NULL COMMENT '操作延后/关闭的时间',
   start_time                 timestamp                      NULL COMMENT 'pad端开始时间',
   keep_photo                 varchar(100)                   DEFAULT NULL COMMENT 'pad端保养照片',
   keep_time                  timestamp                      NULL COMMENT '保养时间',
   mould_id                   varchar(10)                    DEFAULT NULL COMMENT '模具id',
   keep_type              	  integer                        DEFAULT NULL COMMENT '保养类型',
   injection_molding_id       integer                        DEFAULT NULL COMMENT '生产指令id',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模具保养计划下发任务';

/*==============================================================*/
/* Table: 模具任务执行记录                                      */
/*==============================================================*/
drop table if exists fw_mould_plan_execute_record;
create table fw_mould_plan_execute_record
(
   id                         integer                        NOT NULL AUTO_INCREMENT,
   keep_task_id               integer                        DEFAULT NULL COMMENT '设备保养任务对象',
   keep_item_id     	      integer    			   		 DEFAULT NULL COMMENT '设备保养项目',
   keep_item_result    	      varchar(2000)  			   	 DEFAULT NULL COMMENT '设备保养项目结果',
   reason    	              varchar(100)  			   	 DEFAULT NULL COMMENT '不合格原因',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模具任务执行记录';

/*==============================================================*/
/* Table: 分层审核项目                                            */
/*==============================================================*/
drop table if exists fw_audit_item;
create table fw_audit_item
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   name                     varchar(50)                   	DEFAULT NULL COMMENT '项目名称',
   kind                     integer     			  	    DEFAULT NULL COMMENT '项目类型 0人 1机器 2物料 3制度 4环境',
   content                  varchar(200)     			  	DEFAULT NULL COMMENT '内容描述',
   create_time              timestamp                  	    DEFAULT NOW() COMMENT '创建时间',
   deleted					integer						    DEFAULT 0 COMMENT '是否删除 默认0 删除1',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分层审核项目';

/*==============================================================*/
/* Table: 分层审核表单管理                                      */
/*==============================================================*/
drop table if exists fw_audit_team;
create table fw_audit_team
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   name                     varchar(50)                   	DEFAULT NULL COMMENT '审核表单名称',
   item_ids                 varchar(100)     			  	DEFAULT NULL COMMENT '审核项目ids',
   create_time              timestamp                  	    DEFAULT NOW() COMMENT '创建时间',
   deleted					integer						    DEFAULT 0 COMMENT '是否删除 默认0 删除1',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分层审核表单管理';

/*==============================================================*/
/* Table: 分层审核计划制定                                      */
/*==============================================================*/
drop table if exists fw_audit_plan;
create table fw_audit_plan
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   name                     varchar(50)                   	DEFAULT NULL COMMENT '计划名称',
   audit_team_id            integer  			  	        DEFAULT NULL COMMENT '审核表单ID',
   keep_cycle               varchar(50)                  	DEFAULT NULL COMMENT '审核周期',
   cron						varchar(100)					DEFAULT NULL COMMENT 'cron表达式',
   audit_users				varchar(50)						DEFAULT NULL COMMENT '审核人',
   create_time              timestamp                  	    DEFAULT NOW() COMMENT '创建时间',
   status					integer							DEFAULT 0 COMMENT '状态 0可用 1禁用',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分层审核计划制定';



/*==============================================================*/
/* Table: 分层审核任务                                      */
/*==============================================================*/
drop table if exists fw_audit_task;
create table fw_audit_task
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   task_no                  varchar(50)                   	DEFAULT NULL COMMENT '任务单',
   execute_time             timestamp  			  	        NULL COMMENT '执行时间',
   execute_user             integer                  	    DEFAULT NULL COMMENT '执行人',
   status					integer						    DEFAULT 0 COMMENT '执行状态 默认0 0未执行 1已执行',
   audit_plan_id            integer                  	    DEFAULT NULL COMMENT '审核计划ID',
   audit_team_id            integer                  	    DEFAULT NULL COMMENT '审核表单ID',
   create_time              timestamp                  	    DEFAULT NOW() COMMENT '创建时间',
   note                     varchar(200)                   	DEFAULT NULL COMMENT '备注',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分层审核任务';

/*==============================================================*/
/* Table: 审核项目结论                                          */
/*==============================================================*/
drop table if exists fw_audit_item_detail;
create table fw_audit_item_detail
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   audit_task_id            integer                 	    DEFAULT NULL COMMENT '分层审核任务ID',
   audit_item_id            integer  			  	        DEFAULT NULL COMMENT '分层审核项目',
   audit_item_result        integer                  	    DEFAULT NULL COMMENT '0不合格 1合格',
   reason					varchar(200)					DEFAULT 0 COMMENT '原因',
   picture					varchar(200)					DEFAULT NULL COMMENT '图片证据',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审核项目结论';




INSERT INTO fw_jbpm_form values (1, 'devicesKeepRelay', '设备保养延期执行', 'devicesKeepTask/postPonedPass');
INSERT INTO fw_jbpm_form values (2, 'devicesKeepClose', '设备保养强制关闭', 'devicesKeepTask/closedPass');
INSERT INTO fw_jbpm_form values (3, 'devicesRepair', '设备维修', NULL);
INSERT INTO fw_jbpm_form values (4, 'devicesScrap', '设备报废', 'devicesScrap/devicesScrapPass');
INSERT INTO fw_jbpm_form values (5, 'mouldRepair', '模具维修', 'mouldRepair/auditPass');
INSERT INTO fw_jbpm_form values (6, 'cardChange', '工艺卡变更', 'craftCard/cardChangePass');
INSERT INTO fw_jbpm_form values (7, 'mouldTurn', '模具转段', 'mouldDevices/mouldTurnPass');
INSERT INTO fw_jbpm_form values (8, 'pollingCoerceClose', '巡检强制关闭', 'qualityFirstendCheck/updateCoerceCloseStatus');
INSERT INTO fw_jbpm_form values (9, 'pollingPostponeExe', '巡检延期执行', 'qualityFirstendCheck/updatepostponeExeStatus');
INSERT INTO fw_jbpm_form values (10, 'inspectionChange', '检验规范', 'qualityInspection/inspectionChangePass');
INSERT INTO fw_jbpm_form values (11, 'mouldKeepRelay', '模具保养延期执行', 'mouldKeepTask/postPonedPass');
INSERT INTO fw_jbpm_form values (12, 'mouldKeepClose', '模具保养强制关闭', 'mouldKeepTask/closedPass');



INSERT INTO fw_devices_problem VALUES (1, '问题1', '问题1', 0, 0);
INSERT INTO fw_devices_problem VALUES (2, '问题2', '问题2', 0, 1);
INSERT INTO fw_devices_problem VALUES (3, '问题2', '问题3', 0, 2);

/**--------------------------2020-11-1------------------------**/

/*==============================================================*/
/* Table: 物流仓库管理                                           */
/*==============================================================*/
drop table if exists fw_logistics_storage_house;
create table fw_logistics_storage_house
(
   id                   integer                        	NOT NULL AUTO_INCREMENT,
   name                 varchar(50)                     DEFAULT NULL COMMENT '仓库',
   status               integer                         DEFAULT 0    COMMENT '状态 0 正常 1删除',
   all_name             varchar(50)                     DEFAULT NULL COMMENT '全名',
   code                 varchar(20)                     DEFAULT NULL COMMENT '代码',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物流仓库管理';


/*==============================================================*/
/* Table: 物流库位管理                                           */
/*==============================================================*/
drop table if exists fw_logistics_storage_location;
create table fw_logistics_storage_location
(
   id                   integer                        	NOT NULL AUTO_INCREMENT,
   name                 varchar(50)                     DEFAULT NULL COMMENT '库位',
   status               integer                         DEFAULT 0    COMMENT '状态 0 正常 1删除',
   storage_house_id     integer                         DEFAULT NULL COMMENT '仓库',
   all_name             varchar(50)                     DEFAULT NULL COMMENT '全名',
   code                 varchar(20)                     DEFAULT NULL COMMENT '代码',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物流库位管理';

/*==============================================================*/
/* Table: 物料管理                                              */
/*==============================================================*/
drop table if exists fw_logistics_product;
create table fw_logistics_product
(
   id                       integer                        NOT NULL AUTO_INCREMENT,
   name                     varchar(50)                    DEFAULT NULL COMMENT '物料名称',
   code         			varchar(20)     			   DEFAULT NULL COMMENT '物料代码',
   specs                    varchar(50)                    DEFAULT NULL COMMENT '规格型号',
   category					varchar(20)					   DEFAULT NULL COMMENT '物料类型',
   max_store              	integer                        DEFAULT NULL COMMENT '最大库存',
   min_store              	integer                        DEFAULT NULL COMMENT '最小库存',
   warn_time              	integer                        DEFAULT NULL COMMENT '保质期',
   note					    varchar(200)				   DEFAULT NULL COMMENT '备注',
   unit					    varchar(10)				       DEFAULT NULL COMMENT '单位',
   safety_store				decimal(8,2)			       DEFAULT NULL COMMENT '安全库存',
   store_count				decimal(8,2)			       DEFAULT NULL COMMENT '库存量',
   property					varchar(20)			       	   DEFAULT NULL COMMENT '物料属性',
   is_safety			    varchar(5)			       	   DEFAULT NULL COMMENT '是否进行保质期管理',
   update_time			    timestamp		       	       NULL COMMENT '更新时间',

   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物料管理';


/*==============================================================*/
/* Table: 移库                                       */
/*==============================================================*/
drop table if exists fw_logistics_move_house;
create table fw_logistics_move_house
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   move_house_no            varchar(50)                   	DEFAULT NULL COMMENT '移库单号',
   create_user			    integer							DEFAULT NULL COMMENT '创建人',
   create_time              timestamp                       DEFAULT NOW() COMMENT '创建时间',
   status                   integer                  	    DEFAULT 0 COMMENT '0待完成 1已完成 默认0',
   execute_user			    integer							DEFAULT NULL COMMENT '执行人',
   execute_time              timestamp                       NULL COMMENT '执行时间',
   note						varchar(200)					DEFAULT NULL COMMENT '备注',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='移库';


/*==============================================================*/
/* Table: 移库明细                                       */
/*==============================================================*/
drop table if exists fw_logistics_move_house_detail;
create table fw_logistics_move_house_detail
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   move_house_id            integer                   	    DEFAULT NULL COMMENT '移库对象',
   storage_detail_id		integer							DEFAULT NULL COMMENT '物料对象',
   fw_batch                 varchar(50)                     DEFAULT NULL COMMENT '泛沃批次',
   old_loaction			    integer							DEFAULT NULL COMMENT '原库位',
   new_loaction             integer                         DEFAULT NULL COMMENT '新库位',
   storage_count			integer						    DEFAULT NULL COMMENT '移库数量',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='移库明细';

/*==============================================================*/
/* Table: 收货单                                       */
/*==============================================================*/
drop table if exists fw_logistics_take_order;
create table fw_logistics_take_order
(
   id                       integer                        NOT NULL AUTO_INCREMENT,
   task_no                  varchar(50)                   	DEFAULT NULL COMMENT '任务单号',
   order_no			        varchar(50)						DEFAULT NULL COMMENT '源单单号',
   kind              	    integer                         DEFAULT NULL COMMENT '派单类型',
   status                   integer                  	    DEFAULT 0 COMMENT '0待完成 1已完成 默认0',
   take_date				timestamp						NULL COMMENT '采购日期',
   execute_user			    integer							DEFAULT NULL COMMENT '执行人',
   execute_time             timestamp                       NULL COMMENT '执行时间',
   create_time             timestamp                        DEFAULT NOW() COMMENT '创建时间',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收货单';



/*==============================================================*/
/* Table: 收货明细                                     */
/*==============================================================*/
drop table if exists fw_logistics_take_order_detail;
create table fw_logistics_take_order_detail
(
   id                       varchar(100)                    NOT NULL COMMENT 'uuid',
   take_order_id            integer                         DEFAULT NULL COMMENT '收货单ID',
   product_id			    integer					        DEFAULT NULL COMMENT '物料ID',
   buy_count              	integer                         DEFAULT NULL COMMENT '采购数量',
   receive_count            integer                  	    DEFAULT NULL COMMENT '实收数量',
   batch_no				    varchar(50)						DEFAULT NULL COMMENT '泛沃批次号',
   provider_name            varchar(50)						DEFAULT NULL COMMENT '供应商名称',
   note						varchar(200)					DEFAULT NULL COMMENT '备注',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收货明细';


/*==============================================================*/
/* Table: 商品库存                                              */
/*==============================================================*/
drop table if exists fw_logistics_storage_detail;
create table fw_logistics_storage_detail
(
   id                       integer                        NOT NULL AUTO_INCREMENT,
   storage_id              	integer                        DEFAULT 0 COMMENT '库位对象',
   product_id              	integer                        DEFAULT NULL COMMENT '物料',
   storage_count            integer                        DEFAULT NULL COMMENT '数量',
   product_date		        timestamp                      NULL COMMENT '生产日期',
   fw_batch                 varchar(50)                    DEFAULT NULL COMMENT '泛沃批次号',
   provider_name			varchar(50)                    DEFAULT NULL COMMENT '供应商',
   provider_batch			varchar(50)                    DEFAULT NULL COMMENT '供应商批次号',
   storage_date		        timestamp                      DEFAULT NOW() COMMENT '入库日期',
   order_detail_id			varchar(100)                   DEFAULT NULL COMMENT '收货明细Id',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品库存';


/*==============================================================*/
/* Table: 库存操作日志                                          */
/*==============================================================*/
drop table if exists fw_logistics_storage_log;
create table fw_logistics_storage_log
(
   id                       integer                        NOT NULL AUTO_INCREMENT,
   storage_detail_id        integer                        DEFAULT NULL COMMENT '物料库存',
   storage_count            varchar(15)                    DEFAULT NULL COMMENT '库存数量',
   before_count             varchar(15)                    DEFAULT NULL COMMENT '操作前数量',
   after_count              varchar(15)                    DEFAULT NULL COMMENT '操作后数量',
   opreate_type             integer                        DEFAULT NULL COMMENT '操作方式 0入库1出库2退库',
   opreate_detail_id        integer                        DEFAULT NULL COMMENT '操作明细表id',
   opreate_user				integer                        DEFAULT NULL COMMENT '操作人',
   opreate_time		        timestamp                      DEFAULT NOW() COMMENT '操作时间',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存操作日志';


/*==============================================================*/
/* Table: 注塑工艺模型                                          */
/*==============================================================*/
drop table if exists fw_craft_model;
create table fw_craft_model
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   document_code            varchar(50)                   	DEFAULT NULL COMMENT '文档编码',
   product_code             varchar(50)  			  	    DEFAULT NULL COMMENT '产品编码',
   mould_id                 integer                         DEFAULT NULL COMMENT '模具id',
   beat                     integer                         DEFAULT NULL COMMENT '生产节拍',
   product_name             varchar(50)  			  	    DEFAULT NULL COMMENT '产品名称',
   bom_no					varchar(50)					    DEFAULT null COMMENT 'BOM编码',
   model_type               integer                         DEFAULT NULL COMMENT '模型类型',
   material_prepare			integer							DEFAULT NULL COMMENT '物料库房备料',
   picking_time			    integer							DEFAULT NULL COMMENT '领料',
   mixing_time			    integer							DEFAULT NULL COMMENT '拌料',
   feed_time			    integer							DEFAULT NULL COMMENT '投料',
   dry_time			        integer							DEFAULT NULL COMMENT '烘料',
   water_monitor			integer							DEFAULT NULL COMMENT '含水量监测',
   heat_up			        integer							DEFAULT NULL COMMENT '升温',
   first_debug			    integer							DEFAULT NULL COMMENT '首件调试',
   first_check			    integer							DEFAULT NULL COMMENT '首件检验',
   product_prepare			integer							DEFAULT NULL COMMENT '生产库房备料',
   model_out			    integer							DEFAULT NULL COMMENT '出库',
   prenatal_care			integer							DEFAULT NULL COMMENT '产前保养',
   model_debug			    integer							DEFAULT NULL COMMENT '调运至待使用区',
   model_up			        integer							DEFAULT NULL COMMENT '上模',
   model_down			    integer							DEFAULT NULL COMMENT '下模',
   file_ids         	    varchar(200)					DEFAULT NULL COMMENT '作业指导书Ids',
   Inspection_spec         	varchar(200)					DEFAULT NULL COMMENT '检验规范Ids',
   none_sample         	    varchar(200)					DEFAULT NULL COMMENT '不合格封样指导书Ids',
   photo                    varchar(200)                    DEFAULT NULL COMMENT '照片路径',
   product_type	            integer							DEFAULT NULL COMMENT '生产类型 0全自动 1半自动 2手动',
   create_user			    integer							DEFAULT NULL COMMENT '创建人',
   create_time              timestamp                       DEFAULT NOW() COMMENT '创建时间',
   update_user			    integer							DEFAULT NULL COMMENT '修改人',
   update_time              timestamp                       NULL COMMENT '修改时间',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='注塑工艺模型';

/*==============================================================*/
/* Table: 工艺卡管理                                          */
/*==============================================================*/
drop table if exists fw_craft_card;
create table fw_craft_card
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   code                     varchar(20)                   	DEFAULT NULL COMMENT '工艺卡编号',
   name                     varchar(20)  			  	    DEFAULT NULL COMMENT '工艺卡名称',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工艺卡管理';

insert into fw_craft_card(id,code,name) values (1,'0001','日精1');
insert into fw_craft_card(id,code,name) values (2,'0002','日精2');
insert into fw_craft_card(id,code,name) values (3,'0003','雅宝1');
insert into fw_craft_card(id,code,name) values (4,'0004','硅胶机');
insert into fw_craft_card(id,code,name) values (5,'0005','海天');
insert into fw_craft_card(id,code,name) values (6,'0006','今机&丰铁');
insert into fw_craft_card(id,code,name) values (7,'0007','克劳斯玛菲');
insert into fw_craft_card(id,code,name) values (8,'0008','德马格');

/*==============================================================*/
/* Table: 工艺卡参数                                          */
/*==============================================================*/
drop table if exists fw_craft_card_params;
create table fw_craft_card_params
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   card_id                  varchar(20)                   	DEFAULT NULL COMMENT '工艺卡',
   code                     varchar(50)                   	DEFAULT NULL COMMENT '工艺卡编号',
   part_code                varchar(20)  			  	    DEFAULT NULL COMMENT '零件编码',
   part_name                varchar(20)                   	DEFAULT NULL COMMENT '零件名称',
   document_no              varchar(20)  			  	    DEFAULT NULL COMMENT '文档编号',
   product_id               integer                   	    DEFAULT NULL COMMENT '设备编号',
   craft_data               varchar(4000)  			  	    DEFAULT NULL COMMENT '工艺参数',
   create_user              integer                   	    DEFAULT NULL COMMENT '添加人',
   create_time              timestamp  			  	        DEFAULT now() COMMENT '添加时间',
   update_user              integer                   	    DEFAULT NULL COMMENT '变更人',
   update_time              timestamp  			  	        NULL COMMENT '变更时间',
   update_type              integer                   	    DEFAULT '1' COMMENT '变更类型 0 临时变更 1永久变更',
   approve_user             integer  			  	        DEFAULT NULL COMMENT '核准人',
   approve_time             timestamp                   	NULL COMMENT '核准时间',
   status                   integer  			  	        DEFAULT NULL COMMENT '状态 0 可用 1禁用',
   mould_id                 integer  			  	        DEFAULT NULL COMMENT '模具id',

   picture                  long varchar  			  	    DEFAULT NULL COMMENT '图片',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工艺卡参数';

/*==============================================================*/
/* Table: 工艺卡参数变更日志                                          */
/*==============================================================*/
drop table if exists fw_craft_card_log;
create table fw_craft_card_log
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   create_user              integer                   	    DEFAULT NULL COMMENT '变更人员',
   create_time              timestamp  			  	        DEFAULT now() COMMENT '变更时间',
   update_type              integer                   	    DEFAULT NULL COMMENT '变更类型',
   reason                   varchar(200)  			  	    DEFAULT NULL COMMENT '变更原因',
   content                  varchar(500)                    DEFAULT NULL COMMENT '变更内容',
   params_id                integer  			  	        DEFAULT NULL COMMENT '工艺卡参数',
   craft_pre_data           varchar(4000)  			  	    DEFAULT NULL COMMENT '变更前参数',
   craft_after_data         varchar(4000)  			  	    DEFAULT NULL COMMENT '变更后参数',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工艺卡参数变更日志';


/*==============================================================*/
/* Table: 注塑设备列表                                        */
/*==============================================================*/
drop table if exists fw_craft_molde_process;
create table fw_craft_molde_process
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   devices_id               integer                   	    DEFAULT NULL COMMENT '设备ID',
   card_id                  integer  			  	        DEFAULT NULL COMMENT '工艺卡对象ID',
   priority                 integer                  	    DEFAULT NULL COMMENT '优先级 0123',
   craft_model_id			integer					        DEFAULT 0 COMMENT '注塑工艺模型ID',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='注塑设备列表';

/*==============================================================*/
/* Table: 上架                                                  */
/*==============================================================*/
drop table if exists fw_logistics_up_house;
create table fw_logistics_up_house
(
   id                               integer                        NOT NULL AUTO_INCREMENT,
   storage_location_id              integer                        DEFAULT NULL COMMENT '上架库位',
   create_user              	    integer                        DEFAULT NULL COMMENT '操作人',
   create_time                      timestamp                      DEFAULT NOW() COMMENT '操作时间',
   note             				varchar(200)                   DEFAULT NULL COMMENT '备注',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上架';


/*==============================================================*/
/* Table: 上架明细                                                  */
/*==============================================================*/
drop table if exists fw_logistics_up_house_detail;
create table fw_logistics_up_house_detail
(
   id                               integer                        NOT NULL AUTO_INCREMENT,
   storage_detail_id                integer                        DEFAULT NULL COMMENT '物料库存',
   storage_count              	    integer                        DEFAULT NULL COMMENT '数量',
   product_date                     timestamp                      NULL COMMENT '生产日期',
   batch             				varchar(50)                    DEFAULT NULL COMMENT '批次',
   storage_location_id              integer                        DEFAULT NULL COMMENT '库位',
   note             				varchar(200)                   DEFAULT NULL COMMENT '备注',
   up_house_id             		    integer                        DEFAULT NULL COMMENT '上架对象',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上架明细';


/*==============================================================*/
/* Table: 下架                                                  */
/*==============================================================*/
drop table if exists fw_logistics_down_house;
create table fw_logistics_down_house
(
   id                               integer                        NOT NULL AUTO_INCREMENT,
   storage_location_id              integer                        DEFAULT NULL COMMENT '上架库位',
   create_user              	    integer                        DEFAULT NULL COMMENT '操作人',
   create_time                      timestamp                      DEFAULT NOW() COMMENT '操作时间',
   note             				varchar(200)                   DEFAULT NULL COMMENT '备注',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='下架';


/*==============================================================*/
/* Table: 下架明细                                                  */
/*==============================================================*/
drop table if exists fw_logistics_down_house_detail;
create table fw_logistics_down_house_detail
(
   id                               integer                        NOT NULL AUTO_INCREMENT,
   storage_detail_id                integer                        DEFAULT NULL COMMENT '物料库存',
   storage_count              	    integer                        DEFAULT NULL COMMENT '数量',
   product_date                     timestamp                      NULL COMMENT '生产日期',
   batch             				varchar(50)                    DEFAULT NULL COMMENT '批次',
   storage_location_id              integer                        DEFAULT NULL COMMENT '库位',
   note             				varchar(200)                   DEFAULT NULL COMMENT '备注',
   down_house_id             		integer                        DEFAULT NULL COMMENT '上架对象',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='下架明细';

/*==============================================================*/
/* Table: 入库                                                  */
/*==============================================================*/
drop table if exists fw_logistics_store_house;
create table fw_logistics_store_house
(
   id                               integer                        NOT NULL AUTO_INCREMENT,
   house_no                         varchar(50)                    DEFAULT NULL COMMENT '入库单号',
   house_type              	        varchar(20)                    DEFAULT NULL COMMENT '入库类型 0-采购入库 1-委外生产入库 2-生产入库 3-其他入库',
   order_no                         varchar(100)                   DEFAULT NULL COMMENT '关联订单号',
   provider_name             		varchar(50)                    DEFAULT NULL COMMENT '供应商名称',
   create_time                      timestamp                      DEFAULT now() COMMENT '制单时间',
   execute_time             		timestamp                      NULL COMMENT '执行时间',
   execute_user             		integer                        DEFAULT NULL COMMENT '执行人',
   status                           integer                        DEFAULT 0 COMMENT '执行状态 0 未执行1已执行',
   store_date             			timestamp                      NULL COMMENT '入库日期',
   note             		        varchar(200)                   DEFAULT NULL COMMENT '备注',
   parts_type                       integer                        DEFAULT 2 COMMENT '0 成品 1半成品 2 原材料',
   molding_record_ids               long varchar                   DEFAULT NULL COMMENT '报工单id',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入库';

/*==============================================================*/
/* Table: 入库明细                                                  */
/*==============================================================*/
drop table if exists fw_logistics_store_house_detail;
create table fw_logistics_store_house_detail
(
   id                               integer                        NOT NULL AUTO_INCREMENT,
   store_house_id                   integer                        DEFAULT NULL COMMENT '入库对象',
   product_id              	        integer                        DEFAULT NULL COMMENT '物料',
   storage_count                    integer                        DEFAULT NULL COMMENT '实收数量',
   material_count             		char(10)                       DEFAULT NULL COMMENT '物料数量',
   fw_batch                         varchar(50)                    DEFAULT NULL COMMENT '泛沃批次号',
   provider_batch             		varchar(100)                    DEFAULT NULL COMMENT '供应商批次号',
   storage_location_id             	integer                        DEFAULT NULL COMMENT '库位',
   note                             varchar(200)                   DEFAULT NULL COMMENT '备注',
   product_code                     varchar(100)                    DEFAULT NULL COMMENT '生产指令',
   product_devices_id               integer                        DEFAULT NULL COMMENT '生产设备ID',
   order_detail_id                  varchar(100)                   DEFAULT NULL COMMENT '收货明细ID',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入库明细';

/*==============================================================*/
/* Table: 出库                                                  */
/*==============================================================*/
drop table if exists fw_logistics_out_house;
create table fw_logistics_out_house
(
   id                               integer                        NOT NULL AUTO_INCREMENT,
   house_no                         varchar(50)                    DEFAULT NULL COMMENT '出库单号',
   house_type              	        varchar(20)                    DEFAULT NULL COMMENT '出库类型  0-销售出库 1-生产出库 2-委外加工出库 3-其他入库',
   order_no                         varchar(20)                    DEFAULT NULL COMMENT '销售单号',
   create_time                      timestamp                      DEFAULT now() COMMENT '制单时间',
   execute_time             		timestamp                      NULL COMMENT '执行时间',
   execute_user             		integer                        DEFAULT NULL COMMENT '执行人',
   status                           integer                        DEFAULT 0 COMMENT '执行状态 0 未执行1已执行',
   store_date             			timestamp                      NULL COMMENT '出库日期',
   note             		        varchar(200)                   DEFAULT NULL COMMENT '备注',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出库';


/*==============================================================*/
/* Table: 出库明细                                                  */
/*==============================================================*/
drop table if exists fw_logistics_out_house_detail;
create table fw_logistics_out_house_detail
(
   id                               integer                        NOT NULL AUTO_INCREMENT,
   out_house_id                     integer                        DEFAULT NULL COMMENT '出库对象',
   storage_detail_id              	integer                        DEFAULT NULL COMMENT '物料',
   storage_count                    integer                        DEFAULT '0' COMMENT '实出数量',
   apply_count             		    integer                        DEFAULT NULL COMMENT '应出数量',
   fw_batch                         varchar(50)                    DEFAULT NULL COMMENT '泛沃批次号',
   storage_location_id             	integer                        DEFAULT NULL COMMENT '库位',
   note                             varchar(200)                   DEFAULT NULL COMMENT '备注',
   product_order                    varchar(50)                    DEFAULT NULL COMMENT '生产指令',
   feed_count                       integer                        DEFAULT NULL COMMENT '投料数量',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出库明细';

/*==============================================================*/
/* Table: 出库拆包明细                                                  */
/*==============================================================*/
drop table if exists fw_logistics_out_subpackage;
create table fw_logistics_out_subpackage
(
   id                               integer                        NOT NULL AUTO_INCREMENT,
   out_detail_id                    integer                        DEFAULT NULL COMMENT '出库明细',
   batch              	            varchar(50)                    DEFAULT NULL COMMENT '批次号',
   out_count                        varchar(10)                    DEFAULT NULL COMMENT '数量',
   qr_code             		        varchar(200)                   DEFAULT NULL COMMENT '二维码',
   product_code              	    varchar(20)            DEFAULT NULL COMMENT '物料编号',
   product_name                     varchar(50)                  DEFAULT NULL COMMENT '物料名称',
   unit             		        varchar(10)                   DEFAULT NULL COMMENT '单位',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出库拆包明细';



/*==============================================================*/
/* Table: 退库                                              */
/*==============================================================*/
drop table if exists fw_logistics_back_house;
create table fw_logistics_back_house
(
   id                       integer                        NOT NULL AUTO_INCREMENT,
   house_no              	varchar(50)                    DEFAULT NULL COMMENT '退库单号',
   create_time              timestamp                      DEFAULT NOW() COMMENT '制单时间',
   status                   integer                        DEFAULT NULL COMMENT '执行状态',
   execute_time		        timestamp                      NULL COMMENT '执行时间',
   execute_user             integer                        DEFAULT NULL COMMENT '执行人',
   sotre_date				timestamp                      NULL COMMENT '退库日期',
   product_code				varchar(50)                    DEFAULT NULL COMMENT '生产指令',
   note		        		varchar(200)                   NULL COMMENT '备注',
   product_devices_id       integer                        DEFAULT NULL COMMENT '生产设备ID',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退库';




/*==============================================================*/
/* Table: 退库明细                                              */
/*==============================================================*/
drop table if exists fw_logistics_back_house_detail;
create table fw_logistics_back_house_detail
(
   id                       integer                        NOT NULL AUTO_INCREMENT,
   back_house_id            integer                        DEFAULT NULL COMMENT '退库对象',
   product_id               integer                        DEFAULT NULL COMMENT '物料',
   storage_count            integer                        DEFAULT NULL COMMENT '实出数量',
   apply_count		        integer                        DEFAULT NULL COMMENT '应出数量',
   fw_batch                 varchar(50)                    DEFAULT NULL COMMENT '泛沃批次号',
   storage_location_id	    integer                        DEFAULT NULL COMMENT '库位',
   note		        		varchar(200)                   NULL COMMENT '备注',
   provider_batch		    varchar(50)                    NULL COMMENT '供应商批次号',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退库明细';


/*==============================================================*/
/* Table: 盘库                                                  */
/*==============================================================*/
drop table if exists fw_logistics_check_house;
create table fw_logistics_check_house
(
   id                      integer                        NOT NULL AUTO_INCREMENT,
   house_no                varchar(50)                    DEFAULT NULL COMMENT '盘库单号',
   create_user             integer                        DEFAULT NULL COMMENT '制单人',
   create_time             timestamp                      DEFAULT NOW() COMMENT '制单时间',
   check_user              integer                        DEFAULT NULL COMMENT '盘点人',
   check_time              timestamp                      NULL COMMENT '计划盘点日期',
   status                  integer                        DEFAULT 0    COMMENT '执行状态 0 未执行1已执行2关闭',
   execute_time            timestamp                      NULL COMMENT '执行时间',
   execute_user            integer                        DEFAULT NULL COMMENT '执行人',
   check_house_ids         varchar(1000)                   DEFAULT NULL COMMENT '盘点库位',
   note                    varchar(200)                   DEFAULT NULL COMMENT '备注',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='盘库';



/*==============================================================*/
/* Table: 盘库明细                                              */
/*==============================================================*/
drop table if exists fw_logistics_check_house_detail;
create table fw_logistics_check_house_detail
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   check_house_id       integer                        DEFAULT NULL COMMENT '盘库对象',
   storage_id           integer                        DEFAULT NULL COMMENT '盘点库位',
   product_id           integer                        DEFAULT NULL COMMENT '物料',
   storage_count        varchar(20)                    DEFAULT NULL COMMENT '库存数量',
   check_count          varchar(20)                    DEFAULT NULL COMMENT '盘点数量',
   check_result         integer                        DEFAULT NULL COMMENT '盘点结果 0 正常 1异常',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='盘库明细';


/*==============================================================*/
/* Table: 来料检验规范明细                                      */
/*==============================================================*/
drop table if exists fw_quality_inspection_detail;
create table fw_quality_inspection_detail
(
    id                          varchar(100)           NOT NULL,
    feature						varchar(100)           DEFAULT NULL COMMENT '产品特性',
    special						varchar(100)           DEFAULT NULL COMMENT '特殊特性分类',
    process						varchar(100)           DEFAULT NULL COMMENT '工艺（过程）特性',
    standard					varchar(100)           DEFAULT NULL COMMENT '产品/过程规范/公差',
    evaluate					varchar(100)           DEFAULT NULL COMMENT '评价/测量技术',
    measure						varchar(100)           DEFAULT NULL COMMENT '大小',
    quota						varchar(100)           DEFAULT NULL COMMENT '额度',
    control						varchar(100)           DEFAULT NULL COMMENT '控制方法',
    inspection_id				integer                DEFAULT NULL COMMENT '检验规范',
    classify					integer                DEFAULT NULL COMMENT '规范分类 0 外观 1包装 2材质 3尺寸',
    primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='来料检验规范明细';


/*==============================================================*/
/* Table: 检验规范                                      */
/*==============================================================*/
drop table if exists fw_quality_inspection;
create table fw_quality_inspection
(
    id                           integer                NOT NULL AUTO_INCREMENT,
    product_id				    integer                DEFAULT NULL COMMENT '物料对象',
    drawing						varchar(50)            DEFAULT NULL COMMENT '图纸编号',
    customer					varchar(50)            DEFAULT NULL COMMENT '客户',
    classify					integer                DEFAULT NULL COMMENT '规范分类 0 来料 1首末检/巡检 2入库 3出库',
    note						varchar(200)           DEFAULT NULL COMMENT '备注',
    inspection					integer                DEFAULT NULL COMMENT '巡检时间',
    create_user					integer                DEFAULT NULL COMMENT '创建人',
    create_time				    timestamp              DEFAULT NOW() COMMENT '创建时间',
    update_user					integer                DEFAULT NULL COMMENT '修改人',
    update_time					timestamp              NULL COMMENT '修改时间',
    version						varchar(10)            DEFAULT NULL COMMENT '版本号',
    primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='检验规范';



/*==============================================================*/
/* Table: 变更记录                                      */
/*==============================================================*/
drop table if exists fw_quality_inspection_change_log;
create table fw_quality_inspection_change_log
(
    id                          integer                NOT NULL AUTO_INCREMENT,
    reason				    	varchar(200)           DEFAULT NULL COMMENT '变更原因',
    content				    	varchar(200)           DEFAULT NULL COMMENT '变更内容',
    create_user					integer                DEFAULT NULL COMMENT '变更人',
    create_time				    timestamp              DEFAULT NOW() COMMENT '变更时间',
    version						varchar(10)            DEFAULT NULL COMMENT '版本号',
    inspection_id				integer                DEFAULT NULL COMMENT '检验规范',
    inspection_content			text           		   NULL COMMENT '检验规范内容',
    primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='变更记录';


/*==============================================================*/
/* Table: 首末检验管理                                     */
/*==============================================================*/
drop table if exists fw_quality_firstend_check;
create table fw_quality_firstend_check
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   check_no                 varchar(50)                   	DEFAULT NULL COMMENT '检验订单号',
   product_devices_id		integer					        DEFAULT 0 COMMENT '设备编号',
   product_code             varchar(50)                     DEFAULT NULL COMMENT '生产指令',
   mould_no                 varchar(50)                     DEFAULT NULL COMMENT '模具编码',
   product_id               integer                  	    DEFAULT NULL COMMENT '物料对象',
   create_time				timestamp						DEFAULT NOW() COMMENT '报验时间',
   check_type				integer					        DEFAULT NULL COMMENT '0 首件 1巡检 2末件',
   non_execution            varchar(100)                    DEFAULT NULL COMMENT '未执行原因',
   non_execut_cause         varchar(200)                    DEFAULT NULL COMMENT '未执行原因描述',
   carry_day                integer                         DEFAULT 0 COMMENT '延期天数 默认0',
   is_out                   integer                         DEFAULT NULL COMMENT '是否超时 0 未超时 1已超时 ',
   status                   integer                         DEFAULT 0 COMMENT '0待执行 1申请延后 2 强制关闭 3 执行完成',
   check_time				timestamp						NULL COMMENT '检验时间',
   create_user              integer                         DEFAULT NULL COMMENT '用户对象',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='首末检验管理';


/*==============================================================*/
/* Table: 出库检验管理                                     */
/*==============================================================*/
drop table if exists fw_quality_out_check;
create table fw_quality_out_check
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   check_no                 varchar(50)                   	DEFAULT NULL COMMENT '检验订单号',
   out_time		            timestamp					    NULL COMMENT '出货时间',
   customer                 varchar(50)                     DEFAULT NULL COMMENT '客户',
   product_id               integer                  	    DEFAULT NULL COMMENT '物料对象',
   mould_no                 varchar(50)                     DEFAULT NULL COMMENT '模具编码',
   status                   integer                         DEFAULT 0 COMMENT '0待执行 1已执行',
   execute_user				integer							DEFAULT NULL COMMENT '执行人',
   execute_time				timestamp						NULL COMMENT '执行时间',
   create_user              integer                         DEFAULT NULL COMMENT '用户对象',
   create_time				timestamp                       DEFAULT NOW() COMMENT '报检时间',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出库检验管理';

/*==============================================================*/
/* Table: 入库检验管理                                     */
/*==============================================================*/
drop table if exists fw_quality_store_check;
create table fw_quality_store_check
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   check_no                 varchar(50)                   	DEFAULT NULL COMMENT '检验订单号',
   product_devices_id		integer					        DEFAULT NULL COMMENT '设备编号',
   product_code        		varchar(50)  					DEFAULT NULL COMMENT '生产指令',
   mould_no                 varchar(50)                     DEFAULT NULL COMMENT '模具编码',
   product_id               integer                  	    DEFAULT NULL COMMENT '物料对象',
   check_time				timestamp						NULL COMMENT '检验时间',
   status                   integer                         DEFAULT 0 COMMENT '0待执行 1已执行',
   create_user              integer                         DEFAULT NULL COMMENT '用户对象',
   create_time				timestamp                       DEFAULT NOW() COMMENT '报检时间',
   count				    integer                         DEFAULT NULL COMMENT '数量',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入库检验管理';


/*==============================================================*/
/* Table:  来料检验管理                                    */
/*==============================================================*/
drop table if exists fw_quality_material_check;
create table fw_quality_material_check
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   check_no                 varchar(50)                   	DEFAULT NULL COMMENT '检验订单号',
   source_no		        varchar(50)					    DEFAULT NULL COMMENT '源单单号',
   take_order_id            integer							DEFAULT NULL COMMENT '收货单对象',
   buy_date        		    timestamp  					    NULL COMMENT '采购日期',
   take_date                timestamp                  	    NULL COMMENT '收货日期',
   check_date				timestamp						DEFAULT NOW() COMMENT '报验时间',
   status                   integer                         DEFAULT 0 COMMENT '0待执行 1已执行',
   execute_user				integer							DEFAULT NULL COMMENT '执行人',
   execute_time				timestamp						NULL COMMENT '执行时间',
   create_user              integer                         DEFAULT NULL COMMENT '用户对象',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='来料检验管理';


/*==============================================================*/
/* Table:  检验结果                                    */
/*==============================================================*/
drop table if exists fw_quality_inspect_result;
create table fw_quality_inspect_result
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   part_code                varchar(20)                   	DEFAULT NULL COMMENT '零件代码',
   samping		            varchar(20)					    DEFAULT NULL COMMENT '抽样',
   material		            varchar(20)					    DEFAULT NULL COMMENT '材料',
   mould_no		            varchar(20)					    DEFAULT NULL COMMENT '模具编号',
   part_name		         varchar(20)					DEFAULT NULL COMMENT '名称',
   frequence		         varchar(20)					DEFAULT NULL COMMENT '频率',
   material_no		         varchar(20)					DEFAULT NULL COMMENT '材料批号',
   of_no		             varchar(100)					DEFAULT NULL COMMENT '生产指令号',
   end_time		             timestamp					    NULL COMMENT '测量结束时间',
   note		                 varchar(200)					DEFAULT NULL COMMENT '备注',
   create_time				 timestamp						DEFAULT NOW() COMMENT '创建时间',
   user				         integer					    DEFAULT NULL COMMENT '创建人',
   check_classify			 varchar(50)					DEFAULT NULL COMMENT '检验结果分类 0-1 首件外观 0-2首件尺寸 1-1末件外观 1-2 末检尺寸 2-1巡检外观 2-2巡检尺寸  3来料检验 4-0入库外观 4-1入库尺寸 5出库检验',
   real_result				 text					        DEFAULT NULL COMMENT '真实检验结果',
   view_result				 text					        DEFAULT NULL COMMENT '客户检验结果',
   classify				     integer					    DEFAULT NULL COMMENT '规范分类 0 来料 1首末检/巡检 2入库 3出库',
   measure_user              varchar(20)					DEFAULT NULL COMMENT '测量人',
   data_id				     integer					    DEFAULT NULL COMMENT '检验对象',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='检验结果';



/*==============================================================*/
/* Table:  异常联络单                                    */
/*==============================================================*/
drop table if exists fw_quality_excep_list;
create table fw_quality_excep_list
(
   id                       integer                        	NOT NULL AUTO_INCREMENT,
   excep_no                 varchar(20)                    DEFAULT NULL COMMENT '异常单编号',
   create_time              timestamp                      DEFAULT NOW() COMMENT '创建时间',
   product_id               varchar(50)                    DEFAULT NULL COMMENT '物料对象',
   all_count                varchar(10)                    DEFAULT NULL COMMENT '来料数量',
   check_count              varchar(10)                    DEFAULT NULL COMMENT '抽检数量',
   bad_count                varchar(10)                    DEFAULT NULL COMMENT '不良数量',
   batch                    varchar(20)                    DEFAULT NULL COMMENT '来料批次号',
   order_no    				varchar(20)                    DEFAULT NULL COMMENT '采购订单号',
   provider    				varchar(50)                    DEFAULT NULL COMMENT '供应商名称',
   check_user    			varchar(10)                    DEFAULT NULL COMMENT '检验员',
   excep_area 				integer                        DEFAULT NULL COMMENT '异常发生区域',
   bad_desc     			varchar(200)                   DEFAULT NULL COMMENT '不良描述',
   deal_plan    			varchar(200)                   DEFAULT NULL COMMENT '处理方案',
   deal_time    			timestamp                      NULL COMMENT '处理时间',
   prevent_step 			varchar(200)                   DEFAULT NULL COMMENT '原因分析及纠正预防措施',
   prevent_date 			char(10)                       DEFAULT NULL COMMENT '制定日期',
   confirm_result 			varchar(200)                   DEFAULT NULL COMMENT '效果确认',
   confirm_date 			timestamp                      NULL COMMENT '确认日期',
   classify     			integer                        DEFAULT NULL COMMENT '规范分类',
   data_id     				integer                        DEFAULT NULL COMMENT '检验对象',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='异常联络单';



/*==============================================================*/
/* Table: 注塑排产计划                                           */
/*==============================================================*/
drop table if exists fw_plan_injection_molding;
create table fw_plan_injection_molding
(
   id                        integer                        NOT NULL AUTO_INCREMENT,
   product_id                integer                        DEFAULT NULL COMMENT '物料对象',
   plan_count                decimal(8,2)                   DEFAULT NULL COMMENT '计划生产数量',
   product_count             varchar(10)                    DEFAULT NULL COMMENT '实际生产数量',
   product_devices_id        integer                        DEFAULT NULL COMMENT '生产设备',
   mould_id                  integer                        DEFAULT NULL COMMENT '模具',
   start_time                timestamp                      NULL  COMMENT '开始时间',
   priority                  integer                        DEFAULT NULL COMMENT '优先级 0普通 1紧急',
   product_code              varchar(50)                    DEFAULT NULL COMMENT '生产指令',
   material                  varchar(100)                   DEFAULT NULL COMMENT '原材料',
   product_time              decimal(8,2)                   DEFAULT NULL COMMENT '生产时长',
   deliver_time              timestamp                      NULL COMMENT '发货时间',
   note                      varchar(200)                   DEFAULT NULL COMMENT '备注',
   status                    integer                        DEFAULT 0 COMMENT '状态 0 待生产 1生产中 2 暂停 3 取消 4 完成',
   update_user               integer                        DEFAULT NULL COMMENT '修改人',
   update_time               timestamp                      DEFAULT now() COMMENT '修改时间',
   customer_id               integer                        DEFAULT NULL COMMENT '客户',
   actual_start              timestamp                      NULL COMMENT '实际开始时间',
   actual_end                timestamp                      NULL COMMENT '实际完成时间',
   product_device_code       varchar(50)                    DEFAULT NULL COMMENT '设备code',
   create_user               integer                        DEFAULT NULL COMMENT '创建人',
   create_time               timestamp                      DEFAULT now() COMMENT '添加时间',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='注塑排产计划';

/*==============================================================*/
/* Table: 注塑计划暂停列表                                       */
/*==============================================================*/
drop table if exists fw_plan_injection_stop_list;
create table fw_plan_injection_stop_list
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   stop_time            decimal(2,2)                   DEFAULT NULL COMMENT '暂停时长',
   stop_reason          varchar(200)                   DEFAULT NULL COMMENT '暂停原因',
   create_user          integer                        DEFAULT NULL COMMENT '操作人',
   create_time          timestamp                      DEFAULT now() COMMENT '操作时间',
   injection_id         integer                        DEFAULT NULL COMMENT '排查计划',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='注塑计划暂停列表';

/*==============================================================*/
/* Table: 二次加工排产计划                                           */
/*==============================================================*/
drop table if exists fw_plan_rework_injection;
create table fw_plan_rework_injection
(
   id                        integer                        NOT NULL AUTO_INCREMENT,
   product_id                integer                        DEFAULT NULL COMMENT '物料对象',
   plan_count                decimal(8,2)                   DEFAULT NULL COMMENT '计划生产数量',
   product_count             varchar(10)                    DEFAULT NULL COMMENT '实际生产数量',
   start_time                timestamp                      NULL  COMMENT '开始时间',
   priority                  integer                        DEFAULT NULL COMMENT '优先级 0普通 1紧急',
   product_code              varchar(50)                    DEFAULT NULL COMMENT '生产指令',
   deliver_time              timestamp                      NULL COMMENT '发货时间',
   note                      varchar(200)                   DEFAULT NULL COMMENT '备注',
   status                    integer                        DEFAULT 0 COMMENT '状态 0 待生产 1生产中 2 暂停 3 取消 4 完成',
   update_user               integer                        DEFAULT NULL COMMENT '修改人',
   update_time               timestamp                      DEFAULT now() COMMENT '修改时间',
   material                  varchar(100)                   DEFAULT NULL COMMENT '原材料',
   product_time              decimal(8,2)                   DEFAULT NULL COMMENT '生产时长',
   customer_id               integer                        DEFAULT NULL COMMENT '客户',

   create_user               integer                        DEFAULT NULL COMMENT '创建人',
   create_time               timestamp                      DEFAULT now() COMMENT '添加时间',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='二次加工排产计划';
/*==============================================================*/
/* Table: 二次加工排产暂停列表                                       */
/*==============================================================*/
drop table if exists fw_plan_rework_stop_list;
create table fw_plan_rework_stop_list
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   stop_time            decimal(5,2)                   DEFAULT NULL COMMENT '暂停时长',
   stop_reason          varchar(200)                   DEFAULT NULL COMMENT '暂停原因',
   create_user          integer                        DEFAULT NULL COMMENT '操作人',
   create_time          timestamp                      DEFAULT now() COMMENT '操作时间',
   injection_id         integer                        DEFAULT NULL COMMENT '排查计划',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='二次加工排产暂停列表';


/*==============================================================*/
/* Table: 排产客户管理                                  */
/*==============================================================*/
drop table if exists fw_plan_customer;
create table fw_plan_customer
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   name                 varchar(50)                    DEFAULT NULL COMMENT '客户名称',
   status               integer                        DEFAULT 0 COMMENT '状态 0 正常 1删除',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='排产客户管理';
/*==============================================================*/
/* Table: 问题管理                                   */
/*==============================================================*/
drop table if exists fw_audit_question;
create table fw_audit_question
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   question             varchar(100)                   DEFAULT NULL COMMENT '问题',
   question_desc        varchar(200)                   DEFAULT NULL COMMENT '问题描述',
   create_user          integer                        DEFAULT NULL COMMENT '创建人',
   create_time          timestamp                      DEFAULT NOW() COMMENT '创建时间',
   create_file          varchar(100)                   DEFAULT NULL COMMENT '处理前照片',
   duty_user            integer                        DEFAULT NULL COMMENT '责任人',
   deal_user            integer                        DEFAULT NULL COMMENT '处理人',
   deal_time            timestamp                      NULL COMMENT '完成时间',
   deal_result          varchar(200)                   DEFAULT NULL COMMENT '处理结论',
   deal_file            varchar(100)                   DEFAULT NULL COMMENT '处理后照片',
   status               integer                        DEFAULT 0 COMMENT '任务状态',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问题管理';



/*==============================================================*/
/* Table: 发货计划                                              */
/*==============================================================*/
drop table if exists fw_logistics_delivery_plan;
create table fw_logistics_delivery_plan
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   customer             varchar(20)                    DEFAULT NULL COMMENT '客户',
   product_id           integer                        DEFAULT NULL COMMENT '物料',
   plan_count           integer                        DEFAULT NULL COMMENT '计划数量',
   store_time           timestamp                      NULL COMMENT '入库时间',
   arrival_time         timestamp                      NULL COMMENT '到货时间',
   joe_count            integer                        DEFAULT NULL COMMENT '托数',
   status               integer                        DEFAULT 0 COMMENT '发货状态 0未发货 1已发货',
   deliver_time         timestamp                      DEFAULT NOW() COMMENT '发货时间',
   parts_count          integer                        DEFAULT 0 COMMENT '发货数量',
   out_house_id         integer                        DEFAULT NULL COMMENT '出库对象',
   plan_type            integer                        DEFAULT NULL COMMENT '类型 0调拨 1销售',
   note                 varchar(20)                    DEFAULT NULL COMMENT '备注',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发货计划';


/*==============================================================*/
/* Table:  安排员工                                    */
/*==============================================================*/
drop table if exists fw_produce_duty;
create table fw_produce_duty
(
   id                       integer                        NOT NULL AUTO_INCREMENT,
   operate_user             integer                        DEFAULT NULL COMMENT '操作工人',
   create_user              integer                        DEFAULT NULL COMMENT '指派人',
   create_time              timestamp                      DEFAULT NOW() COMMENT '指派时间',
   plan_molding_id          integer                    	   DEFAULT NULL COMMENT '注塑排产计划',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安排员工';


/*==============================================================*/
/* Table:  换班交接                                    */
/*==============================================================*/
drop table if exists fw_produce_shift;
create table fw_produce_shift
(
   id                       integer                        NOT NULL AUTO_INCREMENT,
   operate_user             integer                        DEFAULT NULL COMMENT '交接人',
   create_user              integer                        DEFAULT NULL COMMENT '创建人',
   create_time              timestamp                      DEFAULT NOW() COMMENT '交接时间',
   matters          		varchar(4000)                  DEFAULT NULL COMMENT '交接事项',
   plan_molding_id          integer                        DEFAULT NULL COMMENT '注塑排产计划',
   note          		    varchar(100)                   DEFAULT NULL COMMENT '备注',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='换班交接';


/*==============================================================*/
/* Table:  烘烤时长                                    */
/*==============================================================*/
drop table if exists fw_produce_bake;
create table fw_produce_bake
(
   id                       integer                        NOT NULL AUTO_INCREMENT,
   add_time                 integer                        DEFAULT NULL COMMENT '增加时长',
   create_user              integer                        DEFAULT NULL COMMENT '创建人',
   create_time              timestamp                      DEFAULT NOW() COMMENT '创建时间',
   plan_molding_id          integer                    	   DEFAULT NULL COMMENT '注塑排产计划',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='烘烤时长';

/*==============================================================*/
/* Table: 不良上报                                              */
/*==============================================================*/
drop table if exists fw_produce_bad_report;
create table fw_produce_bad_report
(
    id                      integer                        NOT NULL AUTO_INCREMENT,
    plan_molding_id         integer                        DEFAULT NULL COMMENT'注塑排产计划',
    mold              		varchar(10)                    DEFAULT NULL COMMENT '充模不全',
    material              	varchar(10)                    DEFAULT NULL COMMENT '多料',
    jagged                  varchar(10)                    DEFAULT NULL COMMENT 'Pin高低不齐',
    copper                  varchar(10)                    DEFAULT NULL COMMENT '露铜',
    leakage              	varchar(10)                    DEFAULT NULL COMMENT '漏插簧片',
    cover              		varchar(10)                    DEFAULT NULL COMMENT '簧片覆盖',
    crush              		varchar(10)                    DEFAULT NULL COMMENT '压伤',
    craze              	    varchar(10)                    DEFAULT NULL COMMENT '开裂',
    burning              	varchar(10)                    DEFAULT NULL COMMENT '烧焦',
    oil              	    varchar(10)                    DEFAULT NULL COMMENT '油斑',
    note              	    varchar(100)                   DEFAULT NULL COMMENT '其他',
    create_user             integer                        DEFAULT NULL COMMENT '创建人',
    create_time				timestamp                      DEFAULT NOW() COMMENT '创建时间',
    primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='不良上报';

/*==============================================================*/
/* Table: 生产报工记录                                              */
/*==============================================================*/
drop table if exists fw_produce_molding_record;
create table fw_produce_molding_record
(
   id                       integer                        NOT NULL AUTO_INCREMENT,
   plan_molding_id          integer                        DEFAULT NULL COMMENT '注塑排产计划',
   create_user              integer                        DEFAULT NULL COMMENT '添加人',
   create_time              timestamp                      DEFAULT NOW() COMMENT '创建时间',
   qualified          		integer                    	   DEFAULT NULL COMMENT '本箱合格数量',
   unqualified          	integer                    	   DEFAULT NULL COMMENT '本箱不合格数量',
   bank_status          	integer                    	   DEFAULT 0 COMMENT '0:未入库 1:已入库',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='生产报工记录';

/*==============================================================*/
/* Table:  生产停机记录                                    */
/*==============================================================*/
drop table if exists fw_produce_machine_down;
create table fw_produce_machine_down
(
   id                       integer                        NOT NULL AUTO_INCREMENT,
   plan_molding_id          integer                        DEFAULT NULL COMMENT '注塑排产计划',
   product_devices_id       integer 					   DEFAULT NULL COMMENT '生产设备',
   product_devices_code     varchar(50) 				   DEFAULT NULL COMMENT '生产设备编码',
   real_product_devices_code varchar(50) 				   DEFAULT NULL COMMENT '采集设备编码',
   status       			integer 					   DEFAULT 0 COMMENT '状态 0停机',
   reason              		integer                        DEFAULT NULL COMMENT '停机原因 0保养 1修模 2试模 3换型 4待料 5首模调试 6其它',
   create_user              integer                        DEFAULT NULL COMMENT '添加人',
   create_time              timestamp                      DEFAULT NOW() COMMENT '创建时间 生产停机开始时间',
   end_time                 timestamp                      NULL COMMENT '生产停机结束时间',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='生产停机记录';



/*==============================================================*/
/* Table: 二次加工报工记录                                       */
/*==============================================================*/
drop table if exists fw_produce_rework_record;
create table fw_produce_rework_record
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   plan_rework_id       integer                        DEFAULT NULL COMMENT '二次加工计划',
   create_user          integer                        DEFAULT NULL COMMENT '添加人',
   create_time          timestamp                      DEFAULT NOW() COMMENT '添加时间',
   qualified            integer                        DEFAULT NULL COMMENT '数量',
   status               integer                        DEFAULT 0 COMMENT '状态',
   bank_status          integer                    	   DEFAULT 0 COMMENT '0:未入库 1:已入库',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='二次加工报工记录';


/*==============================================================*/
/* Table: 投料                                                   */
/*==============================================================*/
drop table if exists fw_produce_feeding;
create table fw_produce_feeding
(
    id                      integer                        NOT NULL AUTO_INCREMENT,
    feed_no        			varchar(50)                    DEFAULT NULL COMMENT'领料单号',
    create_user             integer                        DEFAULT NULL COMMENT '制单人',
    product_order        	varchar(50)					   DEFAULT NULL COMMENT '生产指令',
    product_devices_id   	integer                		   DEFAULT NULL COMMENT '生产设备',
    note              	    varchar(100)                   DEFAULT NULL COMMENT '备注',
    create_time				timestamp                      DEFAULT NOW() COMMENT '创建时间',
    primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投料';



/*==============================================================*/
/* Table: 投料明细                                              */
/*==============================================================*/
drop table if exists fw_produce_feeding_detail;
create table fw_produce_feeding_detail
(
    id                      integer                        NOT NULL AUTO_INCREMENT,
    feeding_id        	    integer		                   DEFAULT NULL COMMENT'投料对象',
    storage_detail_id       integer                        DEFAULT NULL COMMENT '物料库存',
    storage_count        	integer					       DEFAULT NULL COMMENT '物料数量',
    primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投料明细';
/*==============================================================*/
/* Table: 领料                                                  */
/*==============================================================*/
drop table if exists fw_logistics_picking;
create table fw_logistics_picking
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   create_time          timestamp                      DEFAULT NOW() COMMENT'领料时间',
   create_user          integer                        DEFAULT NULL COMMENT'领料人',
   out_house_id         integer                        DEFAULT NULL COMMENT'出库对象',
   note                 varchar(200)                   DEFAULT NULL COMMENT'备注',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='领料';



/*==============================================================*/
/* Table:  物料过程监控                                    */
/*==============================================================*/
drop table if exists fw_produce_material_monitor;
create table fw_produce_material_monitor
(
   id                       varchar(100)                   NOT NULL COMMENT 'uuid',
   product_id          		integer                        DEFAULT NULL COMMENT '物料ID',
   product_code          	varchar(50) 				   DEFAULT NULL COMMENT '物料编码',
   product_name          	varchar(50) 				   DEFAULT NULL COMMENT '物料名称',
   take_user       			varchar(10) 				   DEFAULT NULL COMMENT '收货人员',
   take_time       			timestamp 				   	   NULL COMMENT '收货时间',
   check_user       		varchar(10) 				   DEFAULT NULL COMMENT '检验人员',
   check_time       		timestamp 				   	   NULL COMMENT '检验时间',
   store_user       		varchar(10) 				   DEFAULT NULL COMMENT '入库人员',
   store_time       		timestamp 				   	   NULL COMMENT '入库时间',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物料过程监控';

/*==============================================================*/
/* Table:  二次加工过程监控                                    */
/*==============================================================*/
drop table if exists fw_produce_rework_monitor;
create table fw_produce_rework_monitor
(
    id                      integer                        NOT NULL AUTO_INCREMENT,
    plan_molding_id         integer                        DEFAULT NULL COMMENT '二次加工排产计划',
    product_order          	varchar(100) 				   DEFAULT NULL COMMENT '生产指令',
    product_id          	integer 				       DEFAULT NULL COMMENT '零件对象',
    product_code       		varchar(50) 				   DEFAULT NULL COMMENT '零件代码',
    product_name       		varchar(50) 				   NULL COMMENT '零件名称',
    product_start_user      varchar(10) 				   DEFAULT NULL COMMENT '开始生产人员',
    product_start_time      timestamp 				   	   NULL COMMENT '开始生产时间',
    product_end_user       	varchar(10) 				   DEFAULT NULL COMMENT '完成生产人员',
    product_end_time       	timestamp 				   	   NULL COMMENT '完成生产时间',
    check_user       		varchar(10) 				   DEFAULT NULL COMMENT '入库检验人员',
    check_time       		timestamp 				   	   NULL COMMENT '入库检验时间',
    store_user       		varchar(10) 				   DEFAULT NULL COMMENT '入库人员',
    store_time       		timestamp 				   	   NULL COMMENT '入库时间',
    primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='二次加工过程监控';

/*==============================================================*/
/* Table: 注塑过程监控                                          */
/*==============================================================*/
drop table if exists fw_produce_molding_monitor;
create table fw_produce_molding_monitor
(
   id                    integer                        NOT NULL AUTO_INCREMENT,
   plan_molding_id       integer                        DEFAULT NULL COMMENT '注塑排产计划',
   product_order         varchar(100)                   DEFAULT NULL COMMENT '生产指令',
   product_devices_id    integer                        DEFAULT NULL COMMENT '生产设备',
   product_devices_code  varchar(50)                    DEFAULT NULL COMMENT '设备编码',
   product_id            integer                        DEFAULT NULL COMMENT '零件对象',
   product_code          varchar(50)                    DEFAULT NULL COMMENT '零件代码',
   product_name          varchar(50)                    DEFAULT NULL COMMENT '零件名称',
   mould_id              integer                        DEFAULT NULL COMMENT '模具对象',
   mould_code            varchar(50)                    DEFAULT NULL COMMENT '模具编码',
   mould_name            varchar(50)                    DEFAULT NULL COMMENT '模具名称',
   craft_model_id        integer                        DEFAULT NULL COMMENT '工艺卡模型',
   craft_card_id         integer                        DEFAULT NULL COMMENT '工艺卡对象',
   craft_card_name       varchar(50)                    DEFAULT NULL COMMENT '工艺卡名称',
   craft_card_params     mediumtext                     DEFAULT NULL COMMENT '工艺卡参数',
   mould_up_user         varchar(10)                    DEFAULT NULL COMMENT '上模人员',
   mould_up_time         timestamp                      NULL COMMENT '上模时间',
   pick_user             varchar(10)                    DEFAULT NULL COMMENT '领料人员',
   pick_time             timestamp                      NULL COMMENT '领料时间',
   feed_user             varchar(10)                    DEFAULT NULL COMMENT '投料人员',
   feed_time             timestamp                      NULL COMMENT '投料时间',
   first_debug_user      varchar(10)                    DEFAULT NULL COMMENT '首件调试人员',
   first_debug_time      timestamp                      NULL COMMENT '首件调试时间',
   first_check_user      varchar(10)                    DEFAULT NULL COMMENT '首检人员',
   first_check_time      timestamp                      NULL COMMENT '首检时间',
   product_start_user    varchar(10)                    DEFAULT NULL COMMENT '开始生产人员',
   product_start_time    timestamp                      NULL COMMENT '开始生产时间',
   product_end_user      varchar(10)                    DEFAULT NULL COMMENT '完成生产人员',
   product_end_time      timestamp                      NULL COMMENT '完成生产时间',
   last_check_user       varchar(10)                    DEFAULT NULL COMMENT '尾检人员',
   last_check_time       timestamp                      NULL COMMENT '尾检时间',
   store_user            varchar(10)                    DEFAULT NULL COMMENT '入库人员',
   store_time            timestamp                      NULL COMMENT '入库时间',
   status                integer                        DEFAULT NULL COMMENT '生产状态 0调试 1开始生产 2完成生产',
   plan_product_cycle    varchar(10)                    DEFAULT NULL COMMENT '计划生产周期',
   actua_product_cycle   varchar(20)                    DEFAULT NULL COMMENT '实际生产周期',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物料过程监控';

/*==============================================================*/
/* Table:  报废管理                                    */
/*==============================================================*/
drop table if exists fw_logistics_scrap;
create table fw_logistics_scrap
(
   id                       integer                        NOT NULL AUTO_INCREMENT,
   product_id          		integer                        DEFAULT NULL COMMENT '物料ID',
   product_devices_id       integer 				       DEFAULT NULL COMMENT '生产设备ID',
   product_order          	VARCHAR(50) 				   DEFAULT NULL COMMENT '生产指令',
   scrap_coun				integer						   DEFAULT NULL COMMENT '报废数量',
   reason					VARCHAR(200) 				   DEFAULT NULL COMMENT '报废原因',
   create_user              integer                        DEFAULT NULL COMMENT '创建人',
   create_time              timestamp                      DEFAULT NOW() COMMENT '创建时间',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报废管理';

/*==============================================================*/
/* Table: 工艺卡设定值修改记录                                       */
/*==============================================================*/
drop table if exists fw_craft_card_update_param;
create table fw_craft_card_update_param
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   update_old           varchar(10)                    DEFAULT NULL COMMENT '旧值',
   update_new           varchar(10)                    DEFAULT NULL COMMENT '新值',
   update_time          varchar(20)                    DEFAULT NULL COMMENT '修改时间',
   craft_id             varchar(50)                    DEFAULT NULL COMMENT '模具编号',
   update_content       varchar(50)                    DEFAULT NULL COMMENT '修改参数',
   device_code          varchar(50)                    DEFAULT NULL COMMENT '设备编号',
   create_time          timestamp                      DEFAULT NOW() COMMENT '创建时间',
    primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工艺卡设定值修改记录';

/*==============================================================*/
/* Table: 实时状态                                       */
/*==============================================================*/
DROP TABLE IF EXISTS `fw_collect_device_state`;
CREATE TABLE `fw_collect_device_state` (
  `id`                  int(11)                         NOT NULL AUTO_INCREMENT,
  `deviceId`            varchar(255)                    DEFAULT NULL,
  `tmOnlineState`       varchar(255)                    DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: 实时数据                                       */
/*==============================================================*/
DROP TABLE IF EXISTS `fw_collect_device`;
CREATE TABLE `fw_collect_device` (
  `id`                  int(11)                         NOT NULL AUTO_INCREMENT,
  `deviceId`            varchar(255)                    DEFAULT NULL,
  `param_key`           varchar(255)                    DEFAULT NULL,
  `param_value`         varchar(255)                    DEFAULT NULL,
  `create_time`         varchar(255)                    DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2322 DEFAULT CHARSET=utf8;


/*==============================================================*/
/* Table: 一天的历史数据                                      */
/*==============================================================*/
DROP TABLE IF EXISTS `fw_collect_hist_device`;
CREATE TABLE `fw_collect_hist_device` (
  `id`                  int(11)                         NOT NULL AUTO_INCREMENT,
  `deviceId`            varchar(255)                    DEFAULT NULL,
  `param_key`           varchar(255)                    DEFAULT NULL,
  `param_value`         varchar(255)                    DEFAULT NULL,
  `create_time`         varchar(255)                    DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3561 DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: 登录日志                                       */
/*==============================================================*/
drop table if exists fw_pda_login_log;
create table fw_pda_login_log
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   user_id              integer                        DEFAULT NULL COMMENT '登录用户',
   login_time           timestamp                      NULL COMMENT '登录时间',
   exit_time            timestamp                      NULL COMMENT '退出时间',
   product_devices_id   integer                        DEFAULT NULL COMMENT '设备id',
    primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录日志';




/*==============================================================*/
/* Table: ERP领料单                                             */
/*==============================================================*/
drop table if exists fw_logistics_picking_order;
create table fw_logistics_picking_order
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   product_code         varchar(100)                   DEFAULT NULL COMMENT '物料编码',
   product_name         varchar(100)                   DEFAULT NULL COMMENT '物料名称',
   fw_batch             varchar(50)                    DEFAULT NULL COMMENT '泛沃批次号',
   apply_count          integer                        DEFAULT NULL COMMENT '申请数量',
   storage_count        integer                        DEFAULT 0 COMMENT '实发数量',
   create_time          timestamp                      DEFAULT NOW() COMMENT '添加时间',
   out_house_id         integer                        DEFAULT NULL COMMENT '出库对象',
   product_order        varchar(100)                   DEFAULT NULL COMMENT '生产指令',
   unit                 varchar(10)                    DEFAULT NULL COMMENT '单位',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ERP领料单';
