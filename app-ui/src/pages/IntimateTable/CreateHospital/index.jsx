import React, {useState} from 'react';
import {Button, Form, Input, message, Modal} from 'antd';
import axios from "axios";

const CollectionCreateForm = ({visible, onCreate, onCancel}) => {

    const [form] = Form.useForm();
    return (
        <Modal
            visible={visible}
            title="添加医院信息"
            okText="Create"
            cancelText="Cancel"
            onCancel={onCancel}
            onOk={() => {
                form
                    .validateFields()
                    .then((values) => {
                        form.resetFields();
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
                initialValues={{
                    modifier: 'public',
                }}
            >
                <Form.Item
                    name="hospitalName"
                    label="医院名称"
                    rules={[
                        {
                            required: true,
                            message: '医院名称不能为空',
                        },
                    ]}
                >
                    <Input/>
                </Form.Item>
                <Form.Item
                    name="address"
                    label="医院地址"
                    rules={[
                        {
                            required: true,
                            message: '医院地址不能为空',
                        },
                    ]}
                >
                    <Input />
                </Form.Item>
                <Form.Item name="telephoneNumber"
                           label="联系方式"
                           rules={[
                               {   max:11,
                                   min:11,
                                   required: true,
                                   message: '必须为11位且都为数字',
                               },
                           ]}
                           >
                    <input/>
                </Form.Item>
            </Form>
        </Modal>
    );
};

const CreateHospital = (props) => {
    const [visible, setVisible] = useState(false);
    const refresh = props.refresh
    const onCreate = (values) => {
        axios.post("http://localhost:3000/hospital/insert",values).then(res=>{
            if (res.data.flag === true){
                message.success(res.data.message)
                refresh()
            }else {
                message.error(res.data.message)
            }
        })
        setVisible(false);
    };

    return (
        <div>
            <Button
                type="primary"
                onClick={() => {
                    setVisible(true);
                }}
            >
                添加
            </Button>
            <CollectionCreateForm
                visible={visible}
                onCreate={onCreate}
                onCancel={() => {
                    setVisible(false);
                }}
            />
        </div>
    );
};

export default () => <CreateHospital/>;