import React, {useState} from 'react';
import {Button, Form, Input, message, Modal} from 'antd';
import axios from "axios";


function CollectionCreateForm (props){
    const {visible, onCreate, onCancel, data} = props
    let [form] = Form.useForm();
    form.setFieldsValue({
        ...data
    })

    form.setFieldsValue(data)

    return (
        <Modal

            visible={visible}
            title="修改医院信息"
            okText="修改"
            cancelText="取消"
            onCancel={onCancel}
            getContainer={false}
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
                    <Input/>
                </Form.Item>
                <Form.Item name="telephoneNumber"
                           label="联系方式"
                           rules={[
                               {
                                   max: 11,
                                   min: 11,
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

function UpdateHospital(props) {

    let data = props.data
    let refresh = props.refresh
    const [visible, setVisible] = useState(false);

    const onCreate = (values) => {
        values = {id: data.id, ...values}
        axios.post("http://localhost:3000/hospital/update", values).then(res => {
            if (res.data.flag === true) {
                message.success(res.data.message)
                setVisible(false);
                refresh()
            } else {
                message.error(res.data.message)
            }
        })

    };

    return (
        <div>
            <Button
                type="primary"
                onClick={() => {
                    setVisible(true);
                }}
            >
                修改
            </Button>
            <CollectionCreateForm
                visible={visible}
                onCreate={onCreate}
                data={props.data}
                onCancel={() => {
                    setVisible(false);
                }}
            />
        </div>
    );
};

export default UpdateHospital;