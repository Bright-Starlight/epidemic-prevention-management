import React, { useState } from 'react';
import { Modal, Button,message } from 'antd';
import axios from "axios";

function UpdateToConfirm (props) {
    const refresh = props.refresh
    const [isModalVisible, setIsModalVisible] = useState(false);
    const {id} = props
    const showModal = () => {
        setIsModalVisible(true);
    };

    const handleOk = () => {
        updateState()
        setIsModalVisible(false);
    };
    const updateState = () => {
        console.log(id)
        axios.get("http://localhost:3000/carrier/updateToConfirm",{
            params:{
                id:id
            }}).then(res=>{
            if (res.data.flag === true){
                message.success(res.data.message)
                refresh()
            }else {
                message.error(res.data.message)
            }
        })
    };

    const handleCancel = () => {
        setIsModalVisible(false);
    };

    return (
        <>
            <Button type="primary" onClick={showModal} danger>
                已确诊
            </Button>
            <Modal title="警告！！！" visible={isModalVisible} onOk={handleOk} onCancel={handleCancel}>
               是否设为已确诊
            </Modal>
        </>
    );
};

export default UpdateToConfirm;