import {Button, Layout, Menu} from 'antd';
import {createFromIconfontCN} from '@ant-design/icons';
import React, {Component} from 'react';
import HospitalTable from "../HospitalTable";
import {Link, Route, Switch, withRouter} from "react-router-dom";
import IntimateTable from "../IntimateTable";
import IsolationTable from "../IsolationTable";
import IsolationComplete from "../IsolationCompleteTable";
import ConfirmTable from "../ConfirmTable";
import NewConfirmTable from "../NewConfirmTable";
import CureTable from "../CureTable";
import NewCureTable from "../NewCureTable";
import DieTable from "../DieTable";
import NewDieTable from "../NewDieTable";
import UserTable from "../UserTable";
import Report from "../Report";
import NewBrokenLine from "../Report/NewBrokenLine";


const {Header, Content, Sider} = Layout;
const IconFont = createFromIconfontCN({
    scriptUrl: [
        "//at.alicdn.com/t/font_3417669_413xgr4hmez.js"
    ],
});
const list = [
    {
        icon: <IconFont style={{fontSize: "40px"}} type="icon-xgyq"/>,
        children: [{title: "新增", key: "newReport", url: '/main/newReport'},{title: "累计", key: "report", url: '/main/report'}],
        key: 1
    },
    {
        icon: <IconFont style={{fontSize: "40px"}} type="icon-yiyuan"/>,
        children: [{title: "医院管理", key: "hospital", url: '/main/hospital'}],
        key: 2
    },
    {
        icon: <IconFont style={{fontSize: "40px"}} type="icon-shenqingjilu"/>,
        children: [{title: "新增密接", key: "intimate", url: '/main/intimate'},
            {
            title: "正在隔离",
            key: "inIsolation",
            url: "/main/inIsolation"
        }, {title: "隔离完成", key: "isolationFinish",url: "/main/isolationFinish"}],
        key: 3
    },
    {
        icon: <IconFont style={{fontSize: "40px"}} type="icon-jiuhuche"/>,
        children: [{title: "新增确诊", key: "newConfirm",url: "/main/newConfirm"}, {title: "确诊列表", key: "confirm",url: "/main/confirm"}],
        key: 4
    },
    {
        icon: <IconFont style={{fontSize: "40px"}} type="icon-jijiubao"/>,
        children: [{title: "新增自愈", key: "die",url: "/main/newCure"},{title: "自愈列表", key: "cure",url: "/main/cure"}],
        key: 5
    },
    {
        icon: <IconFont style={{fontSize: "40px"}} type="icon-aixin"/>,
        children: [{title: "新增遇难", key: "newDie",url: "/main/newDie"},{title: "遇难列表", key: "die",url: "/main/die"}],
        key: 6
    },
    {
        icon: <IconFont style={{fontSize: "40px"}} type="icon-yonghu"/>,
        children: [{title: "医务人员列表", key: "user", url: '/main/user'}],
        key: 7
    },
]
const titles = [
    "控制台首页",
    "医院管理",
    "密切接触管理",
    "确诊管理",
    "治愈管理",
    "遇难管理",
    "医务人员详情"
]


class Main extends Component{

    componentDidMount() {
        this.props.history.push("/main/NewReport")
    }

    render() {
        return (
            <div>
                <Layout>
                    <Sider style={{backgroundColor: "white"}}
                           breakpoint="lg"
                           collapsedWidth="0"
                           onBreakpoint={broken => {
                               console.log(broken);
                           }}
                           onCollapse={(collapsed, type) => {
                               console.log(collapsed, type);
                           }}
                    >
                        <div className="logo">
                            <img src={"/image/logo1.png"} alt={"首页"} style={{width:"200px",height:"80px"}}/>
                        </div>
                        <Menu
                            theme="light "
                            mode="inline"
                            defaultSelectedKeys={["1"]}
                            items={list.map(
                                (list) => ({
                                    key: String(list.key),
                                    icon: list.icon,
                                    label: `${titles[list.key - 1]}`,
                                    style: {padding: "10px 0"},
                                    children: list.children.map((child) => ({
                                        key: String(child.key),
                                        icon: <Link to={child.url}>{child.title}</Link>,
                                    }))
                                }),
                            )}
                        />
                    </Sider>
                    <Layout>
                        <Header className="site-layout-sub-header-background"
                                style={{padding: 0, backgroundColor: "white"}}>
                            <Button onClick={()=>{ this.props.history.push("/")} }type="primary">退出登录</Button>
                        </Header>
                        <Content>
                            <div className="site-layout-background" style={{padding: 24, minHeight: 360}}>
                                <Switch>
                                    <Route path="/main/report" component={Report}/>
                                    <Route path="/main/NewReport" component={NewBrokenLine}/>
                                    <Route path="/main/intimate" component={IntimateTable}/>
                                    <Route path="/main/hospital" component={HospitalTable}/>
                                    <Route path="/main/inIsolation" component={IsolationTable}/>
                                    <Route path="/main/isolationFinish" component={IsolationComplete}/>
                                    <Route path="/main/confirm" component={ConfirmTable}/>
                                    <Route path="/main/newConfirm" component={NewConfirmTable}/>
                                    <Route path="/main/cure" component={CureTable}/>
                                    <Route path="/main/newCure" component={NewCureTable}/>
                                    <Route path="/main/die" component={DieTable}/>
                                    <Route path="/main/newDie" component={NewDieTable}/>
                                    <Route path="/main/user" component={UserTable}/>
                                </Switch>
                            </div>
                        </Content>
                    </Layout>
                </Layout>
            </div>
        );
    }



}

export default withRouter(Main) ;


