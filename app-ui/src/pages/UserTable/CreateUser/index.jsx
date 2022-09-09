import React, {useState} from 'react';
import {Button, DatePicker, Form, Input, message, Modal,Select} from 'antd';

import {API} from "../../../api";

const { Option } = Select;




function CollectionCreateForm  ({visible, onCreate, onCancel,data})  {
    const [form] = Form.useForm();
    console.log(data,"------------------")
    return (
        <Modal
            visible={visible}
            title="添加医院信息"
            okText="添加"
            cancelText="取消"
            destroyOnClose
            onCancel={onCancel}
            onOk={() => {
                form
                    .validateFields()
                    .then((values) => {
                        onCreate(values);
                    })
                    .catch((info) => {
                        console.log('Validate Failed:', info);
                    });
            }}
        >
            <Form
                form={form}
                layout="vertical"
                name="form_in_modal"
                preserve={false}
                initialValues={{
                    modifier: 'public',
                }}
            >
                <Form.Item
                    name="userName"
                    label="用户名"
                    style={{display: 'inline-block', width: 'calc(50% - 8px)'}}
                    rules={[
                        {
                            required: true,
                            message: '用户名不能为空',
                        },
                    ]}
                >
                    <Input/>
                </Form.Item>

                <Form.Item
                    name="name"
                    label="姓名"
                    style={{display: 'inline-block', width: 'calc(50% - 8px)'}}
                    rules={[
                        {
                            required: true,
                            message: '姓名不能为空',
                        },
                    ]}
                >
                    <Input/>
                </Form.Item>
                <Form.Item
                    name="gender"
                    label="性别"
                    style={{display: 'inline-block', width: 'calc(50% - 8px)'}}
                    rules={[
                        {
                            required: true,
                            message: '性别不能为空',
                        },
                    ]}
                >
                    <Input />
                </Form.Item>

                <Form.Item
                    name="password"
                    label="密码"
                    style={{display: 'inline-block', width: 'calc(50% - 8px)', margin: '0 8px'}}
                    rules={[
                        {
                            required: true,
                            message: '密码不能为空',
                        },
                    ]}
                >
                    <Input/>
                </Form.Item>
                <Form.Item
                    name="empowerInfo"
                    label="权限"
                    style={{display: 'inline-block', width: 'calc(50% - 8px)', margin: '0 8px'}}
                    rules={[
                        {
                            required: true,
                            message: '密码不能为空',
                        },
                    ]}
                >
                    <Select>
                        <Option value={"1"}>管理员</Option>
                        <Option value={"2"}>员工</Option>
                    </Select>
                </Form.Item>
            </Form>
        </Modal>
    );
};

function CreateUser  (props)  {
    const [visible, setVisible] = useState(false);
    const refresh = props.refresh
    const [data] = useState([]);
    const onCreate = (values) => {
        values.updateName = localStorage.getItem("name")
        values.createName = localStorage.getItem("name")
        API.post("http://localhost:3000/user/insert", values).then(res => {
            if (res.data.flag === true) {
                message.success(res.data.message)
                refresh()
                setVisible(false);
            } else {
                message.error(res.data.message)
            }
        })

    };

    return (
        <div>
            <Button
                type="primary"
                onClick={() =>  {
                    setVisible(true);
                }}
            >
                添加
            </Button>
            <CollectionCreateForm
                data={data}
                visible={visible}
                onCreate={onCreate}
                onCancel={() => {
                    setVisible(false);
                }}
            />
        </div>
    );
};

export default CreateUser;