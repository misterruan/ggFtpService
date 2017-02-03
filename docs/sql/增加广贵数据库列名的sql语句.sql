-- 持仓
ALTER TABLE t_FdsiPushCustomerHoldPosition ADD userid DECIMAL(10,0) NULL COMMENT '用户标识' AFTER `updatetime`;
ALTER TABLE t_FdsiPushCustomerHoldPosition ADD openquantity DECIMAL(10,0) NULL COMMENT '建仓量' AFTER `userid`;
ALTER TABLE t_FdsiPushCustomerHoldPosition ADD commoditycode VARCHAR(45) NULL COMMENT '商品代码' AFTER `openquantity`;


-- 平仓
ALTER TABLE t_FdsiPushCustomerClosePosition ADD COLUMN `openid`  DECIMAL(19,0) NULL COMMENT '建仓单号' AFTER `updatetime`;
ALTER TABLE t_FdsiPushCustomerClosePosition ADD COLUMN `opendirector`  int(11) NULL COMMENT '建仓方向' AFTER `openid`;
ALTER TABLE t_FdsiPushCustomerClosePosition ADD COLUMN `userid` DECIMAL(12,0) NULL COMMENT '用户标识' AFTER `opendirector`;
ALTER TABLE t_FdsiPushCustomerClosePosition ADD COLUMN `opentime` bigint(20) NULL COMMENT '建仓时间' AFTER `userid`;
ALTER TABLE t_FdsiPushCustomerClosePosition ADD COLUMN `commoditycode` VARCHAR(45) NULL COMMENT '商品代码' AFTER `opentime`;


-- 限价
ALTER TABLE t_FdsiPushCustomerLimitPrice ADD COLUMN `dealstatus` varchar(100) NULL COMMENT '处理状态' AFTER `updatetime`;
ALTER TABLE t_FdsiPushCustomerLimitPrice ADD COLUMN `userid` decimal(10,0) NULL COMMENT '用户标识' AFTER `dealstatus`;
ALTER TABLE t_FdsiPushCustomerLimitPrice ADD COLUMN `customername` varchar(128) NULL COMMENT '姓名' AFTER `userid`;
ALTER TABLE t_FdsiPushCustomerLimitPrice ADD COLUMN `expiretype` varchar(45) NULL COMMENT '期限(中文)' AFTER `customername`;
ALTER TABLE t_FdsiPushCustomerLimitPrice ADD COLUMN `commoditycode` varchar(45) NULL COMMENT '商品代码' AFTER `expiretype`;


-- 资金流水
ALTER TABLE t_FdsiQueryCustomerCashFlow ADD COLUMN `userid` decimal(10,0) NULL COMMENT '用户标识' AFTER `updatetime`;
ALTER TABLE t_FdsiQueryCustomerCashFlow ADD COLUMN `changetype` varchar(45) NULL COMMENT '客户账户变动的类型（中文）' AFTER `userid`;


-- 客户账户/出入金
ALTER TABLE t_FdsiPushCustomerInOutMoney ADD COLUMN `userid` decimal(10,0) NULL COMMENT '用户标识' AFTER `updatetime`;
ALTER TABLE t_FdsiPushCustomerInOutMoney ADD COLUMN `amountin` decimal(10,0) NULL COMMENT '日入金累计' AFTER `userid`;
ALTER TABLE t_FdsiPushCustomerInOutMoney ADD COLUMN `amountout` decimal(10,0) NULL COMMENT '日出金累计' AFTER `amountin`;



-- 删除平仓唯一性索引
ALTER TABLE `t_FdsiPushCustomerClosePosition`
DROP INDEX `UNIQUE` ;

-- 删除持仓唯一性索引
ALTER TABLE `t_FdsiPushCustomerHoldPosition`
DROP INDEX `UNIQUE` ;

-- 删除限价唯一性索引
ALTER TABLE `t_FdsiPushCustomerLimitPrice`
DROP INDEX `UNIQUE` ;

-- 删除资金流水唯一性索引
ALTER TABLE `t_FdsiQueryCustomerCashFlow`
DROP INDEX `UNIQUE` ;

-- 删除客户账户/出入金唯一性索引
ALTER TABLE `t_FdsiPushCustomerInOutMoney`
DROP INDEX `UNIQUE` ;