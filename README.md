[目录](SUMMARY.md)

- 一共27个接口分为4个模块
- 先按接口做，包功能分为模块。
- 数据是核心，界面要围绕核心来展开。

登录
       192.168.10.158:80/Interface/Login.aspx
http://192.168.10.158:80/Interface/AppInterfaceLogin.ashx?U_Number=00004&U_Pwd=123456
查询
http://192.168.10.158:80/Interface/NonCivilAppDownList.ashx?U_Id=42
http://192.168.10.158:80/Interface/NonCivilAppDownListSPIdAddressDetial.ashx?U_Id=42&&SP_Id=225
下载
http://192.168.10.158:80/Interface/NonCivilAppDownListDetial.ashx?U_Id=42&&SP_Id=215
http://192.168.10.158:80/Interface/NonCivilAppDownListTwoDetial.ashx?U_Id=42&&SP_Id=215&&Addressid
=1&&NCT_Id=215
部分下载的取消
http://192.168.10.158:80/Interface/doNonciviReciveState.ashx?U_Id=42&&SP_Id=217&&State=2&&AddressI
d=3&&NCT_Id=217

以后任何单独的东西，都要单独的写一个表，比如补充安检项，和id向挂钩。