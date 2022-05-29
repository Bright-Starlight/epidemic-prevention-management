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
                    name="identityCard"
                    label="身份证号码"
                    rules={[
                        {
                            required: true,
                            max: 18,
                            min: 18,
                            message: '身份证号码不能为空或小于18为',
                        },
                    ]}
                >
                    <Input />
                </Form.Item>

                <Form.Item
                    name="age"
                    label="年龄"
                    style={{display: 'inline-block', width: 'calc(50% - 8px)'}}
                    rules={[
                        {
                            required: true,
                            message: '年龄不能为空',
                        },
                    ]}
                >
                    <Input type={"number"} showCount={true}/>
                </Form.Item>

                <Form.Item
                    name="gender"
                    label="性别"
                    style={{display: 'inline-block', width: 'calc(50% - 8px)', margin: '0 8px'}}
                    rules={[
                        {
                            required: true,
                            message: '性别不能为空',
                        },
                    ]}
                >
                    <Input/>
                </Form.Item>

                <Form.Item
                    name="homeAddress"
                    label="家庭住址"
                    style={{display: 'inline-block', width: 'calc(50% - 8px)'}}
                    rules={[
                        {
                            required: true,
                            message: '家庭住址不能为空',
                        },
                    ]}
                >
                    <Input/>
                </Form.Item>

                <Form.Item
                    name="telephoneNumber"
                    label="联系方式"
                    style={{display: 'inline-block', width: 'calc(50% - 8px)', margin: '0 8px'}}
                    rules={[
                        {
                            max: 11,
                            min: 11,
                            required: true,
                            message: '联系方式不能为空或不为11位',
                        },
                    ]}
                ><Input type={"number"}/>
                </Form.Item>

                <Form.Item
                    name="cause"
                    label="接触原因"
                    style={{display: 'inline-block', width: 'calc(50% - 8px)'}}
                    rules={[
                        {
                            required: true,
                            message: '接触原因不能为空',
                        },
                    ]}
                >
                    <Input/>
                </Form.Item>

                <Form.Item
                    name="fromHospital"
                    label="所属医院"
                    style={{display: 'inline-block', width: 'calc(50% - 8px)', margin: '0 8px'}}
                    rules={[
                        {
                            required: true,
                            message: '所属医院不能为空',
                        },
                    ]}
                >
                    <Select
                        size="default"
                        // onChange={handleChange}
                        style={{
                            width: 200,
                        }}
                    >
                        {
                            list.map(obj=>{
                                return(<Option key={obj.id} value={obj.id}>{obj.hospitalName}</Option>)
                            })
                        }
                    </Select>
                </Form.Item>

                <Form.Item
                    name="isolationTime"
                    label="隔离时间"
                    rules={[
                        {
                            required: true,
                            message: '隔离时间不能为空',
                        },
                    ]}
                >
                    <DatePicker />
                </Form.Item>
            </Form>
        </Modal>
    );
};

function UpdateConfirm(props) {
    console.log(props)
    let data = props.data
    let refresh = props.refresh
    const [visible, setVisible] = useState(false);
    const [list, setList] = useState([]);

    const onCreate = (values) => {
        values = {id: data.id, ...values}
        API.post("http://localhost:3000/carrier/update", values).then(res => {
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
                    API.get("http://localhost:3000/hospital/getAll").then(res=>{
                        if (res.data.flag === true){
                            setList(res.data.data)
                        }else {
                            message.error(res.data.message)
                        }
                    })
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

export default UpdateConfirm;