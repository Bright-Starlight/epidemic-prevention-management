import {Button,  Form, Input, message} from 'antd';
import { withRouter} from "react-router-dom";

import {API} from "../../api";


function Login  (props){
    const onFinish = (values) => {
        API.post("http://localhost:3000/user/login",values).then(res=>{
            if(res.data.flag === true){
                props.history.push("/main")
                localStorage.setItem("id",res.data.data.id)
                localStorage.setItem("name",res.data.data.name)
                message.success(res.data.message)
            }else {
                message.error(res.data.message);
            }
        })
        console.log('Success:', values);
    };
    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

    return (
        <div>
        <Form
            name="basic"
            labelCol={{
                span: 8,
            }}
            wrapperCol={{
                span: 16,
            }}

            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
            autoComplete="off"
        >
            <Form.Item
                label="用户名"
                name="userName"
                rules={[
                    {
                        required: true,
                        message: 'Please input your username!',
                    },
                ]}
            >
                <Input />
            </Form.Item>

            <Form.Item
                label="密码"
                name="password"
                rules={[
                    {
                        required: true,
                        message: 'Please input your password!',
                    },
                ]}
            >
                <Input.Password />
            </Form.Item>


            <Form.Item
                wrapperCol={{
                    offset: 8,
                    span: 16,
                }}
            >
                <Button type="primary" htmlType="submit">
                    登录
                </Button>
            </Form.Item>
        </Form>
   </div>
    );
};

export default withRouter(Login);