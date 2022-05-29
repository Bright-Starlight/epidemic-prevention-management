import React, {useState} from 'react';
import {Button, DatePicker, Form, Input, message, Modal,Select} from 'antd';
import {API} from "../../../api";

const { Option } = Select;


const dateFormat = 'YYYY/MM/DD';

function CollectionCreateForm  ({visible, onCreate, onCancel,data})  {
    const [form] = Form.useForm();
    console.log(data,"------------------")
    return (
        <Modal
            visible={visible}
            title="添加医院信息"
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
                        defaultValue="选择医院"
                        // onChange={handleChange}
                        style={{
                            width: 200,
                        }}
                    >
                        {
                            data.map(obj=>{
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
                    <DatePicker  format={dateFormat} />
                </Form.Item>
            </Form>
        </Modal>
    );
};

function CreateDie  (props)  {
    const [visible, setVisible] = useState(false);
    const refresh = props.refresh
    const [data, setData] = useState([]);
    const onCreate = (values) => {
        API.post("http://localhost:3000/carrier/insertDie", values).then(res => {
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
                onClick={() => {
                    API.get("http://localhost:3000/hospital/getAll").then(res=>{
                        if (res.data.flag === true){
                            setData(res.data.data)

                        }else {
                            message.error(res.data.message)
                        }
                    })
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

export default CreateDie;