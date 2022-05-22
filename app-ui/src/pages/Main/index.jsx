import {Layout, Menu} from 'antd';
import {createFromIconfontCN} from '@ant-design/icons';
import React, {Component} from 'react';
import HospitalTable from "../HospitalTable";
import {Link, Route, Switch, withRouter} from "react-router-dom";


const {Header, Content, Sider} = Layout;
const IconFont = createFromIconfontCN({
    scriptUrl: [
        "//at.alicdn.com/t/font_3417669_413xgr4hmez.js"
    ],
});
const list = [
    {
        icon: <IconFont style={{fontSize: "40px"}} type="icon-xgyq"/>,
        children: [{title: "数据详情", key: "report", url: '/report'}],
        key: 1
    },
    {
        icon: <IconFont style={{fontSize: "40px"}} type="icon-yiyuan"/>,
        children: [{title: "医院管理", key: "hospital", url: '/hospital'}],
        key: 2
    },
    {
        icon: <IconFont style={{fontSize: "40px"}} type="icon-shenqingjilu"/>,
        children: [{title: "新增密接", key: "intimate", url: '/intimate'},
            {
            title: "正在隔离",
            key: "inIsolation",
            url: "/inIsolation"
        }, {title: "隔离完成", key: "isolationFinish",url: "/isolationFinish"}],
        key: 3
    },
    {
        icon: <IconFont style={{fontSize: "40px"}} type="icon-jiuhuche"/>,
        children: [{title: "新增确诊", key: "newConfirm",url: "/newConfirm"}, {title: "确诊列表", key: "confirm",url: "/Confirm"}],
        key: 4
    },
    {
        icon: <IconFont style={{fontSize: "40px"}} type="icon-jijiubao"/>,
        children: [{title: "自愈列表", key: "cure",url: "/cure"}],
        key: 5
    },
    {
        icon: <IconFont style={{fontSize: "40px"}} type="icon-aixin"/>,
        children: [{title: "遇难列表", key: "die",url: "/die"}],
        key: 6
    },
    {
        icon: <IconFont style={{fontSize: "40px"}} type="icon-yonghu"/>,
        children: [{title: "医务人员列表", key: "user", url: '/user'}],
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
        const history = this.props.history
        history.push("/report")
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
                            <img src={"/image/logo.png"} alt={"首页"}/>
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
                                style={{padding: 0, backgroundColor: "white"}}/>
                        <Content>
                            <div className="site-layout-background" style={{padding: 24, minHeight: 360}}>
                                <Switch>
                                    <Route path="/hospital" component={HospitalTable}/>
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


