import React, {useState} from 'react';
import {Button, DatePicker, Form, Input, message, Modal, Select} from 'antd';

import {API} from "../../../api";
import moment from 'moment';

const { Option } = Select;

function CollectionCreateForm (props){
    const {visible, onCreate, onCancel, data,list} = props
    data.isolationTime = moment(data.isolationTime)
    let [form] = Form.useForm();

    form.setFieldsValue({
        ...data
    })
    form.setFieldsValue(data)
    return (
        <Modal
            visible={visible}
            title="添加用户"
            okText="添加"
            cancelText="取消"
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
                initialValues={{
                    modifier: 'public',
                }}
            >

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
                    style={{display: 'inline-block', width: 'calc(50% - 8px)', margin: '0 8px'}}
                    name="gender"
                    label="性别"
                    rules={[
                        {
                            required: true,
                            message: '性别不能为空',
                        },
                    ]}
                >
                    <Input />
                </Form.Item>
            </Form>
        </Modal>
    );
};

function UpdateUser(props) {
    console.log(props)
    let data = props.data
    let refresh = props.refresh
    const [visible, setVisible] = useState(false);
    const [list, setList] = useState([]);

    const onCreate = (values) => {
        values = {id: data.id, ...values}
        API.post("http://localhost:3000/user/update", values).then(res => {
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
                onClick={() =>  {
                    setVisible(true);
                }}
            >
                修改
            </Button>
            <CollectionCreateForm
                list={list}
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

export default UpdateUser;