import {Button, Input, message, Space, Table} from 'antd';
import Highlighter from 'react-highlight-words';
import {SearchOutlined} from '@ant-design/icons';
import React from 'react';
import CreateHospital from './CreateHospital/index'
import UpdateHospital from "./UpdateHospital/index";

import {API} from "../../api";
import DeleteHospital from "./DeleteHospital";



class HospitalTable extends React.Component {


   componentDidMount() {
       this.page = 1
       this.pageSize = 5
       API.get("http://localhost:3000/hospital/getData",{params:{
           page:1,
               pageSize:5
           }}).then(
           res => {
               if (res.data.flag === true){
                   let data = res.data.data.list
                   this.setState({data:[...data],total:res.data.data.total})
               }else {
                   message.error(res.data.message)
               }

           }
       )
   }


    state = {data:[],
        searchText: '',
        searchedColumn: '',
    };
    page = 0;
    pageSize = 0;

   onChange = (page,pageSize)=>{
       this.page = page
       this.pageSize = pageSize
       API.get("http://localhost:3000/hospital/getData",{params:{
               page:page,
               pageSize:pageSize
           }}).then(
           res => {
               if (res.data.flag === true){
                   let data = res.data.data.list
                   this.setState({data:[...data],total:res.data.data.total})
               }else {
                   message.error(res.data.message)
               }

           }
       )
   }

    getColumnSearchProps = dataIndex => ({
        filterDropdown: ({setSelectedKeys, selectedKeys, confirm, clearFilters}) => (
            <div style={{padding: 8}}>
                <Input
                    ref={node => {
                        this.searchInput = node;
                    }}
                    placeholder={`搜索 ${dataIndex}`}
                    value={selectedKeys[0]}
                    onChange={e => setSelectedKeys(e.target.value ? [e.target.value] : [])}
                    onPressEnter={() => this.handleSearch(selectedKeys, confirm, dataIndex)}
                    style={{marginBottom: 8, display: 'block'}}
                />
                <Space>
                    <Button
                        type="primary"
                        onClick={() => this.handleSearch(selectedKeys, confirm, dataIndex)}
                        icon={<SearchOutlined/>}
                        size="small"
                        style={{width: 90}}
                    >
                        搜索
                    </Button>
                    <Button onClick={() => this.handleReset(clearFilters)} size="small" style={{width: 90}}>
                        清空
                    </Button>
                    <Button
                        type="link"
                        size="small"
                        onClick={() => {
                            confirm({closeDropdown: false});
                            this.setState({
                                searchText: selectedKeys[0],
                                searchedColumn: dataIndex,
                            });
                        }}
                    >
                        Filter
                    </Button>
                </Space>
            </div>
        ),
        filterIcon: filtered => <SearchOutlined style={{color: filtered ? '#1890ff' : undefined}}/>,
        onFilter: (value, record) =>
            record[dataIndex]
                ? record[dataIndex].toString().toLowerCase().includes(value.toLowerCase())
                : '',
        onFilterDropdownVisibleChange: visible => {
            if (visible) {
                setTimeout(() => this.searchInput.select(), 100);
            }
        },
        render: text =>
            this.state.searchedColumn === dataIndex ? (
                <Highlighter
                    highlightStyle={{backgroundColor: '#ffc069', padding: 0}}
                    searchWords={[this.state.searchText]}
                    autoEscape
                    textToHighlight={text ? text.toString() : ''}
                />
            ) : (
                text
            ),
    });

    handleSearch = (selectedKeys, confirm, dataIndex) => {
        confirm();
        this.setState({
            searchText: selectedKeys[0],
            searchedColumn: dataIndex,
        });
    };

    handleReset = clearFilters => {
        clearFilters();
        this.setState({searchText: ''});
    };


    render() {

        const columns = [
            {
                title: '医院名称',
                dataIndex: 'hospitalName',
                key: 'hospitalName',
                width: '20%',
                ...this.getColumnSearchProps('hospitalName'),
            },
            {
                title: '医院地址',
                dataIndex: 'address',
                key: 'address',
                width: '20%',
                ...this.getColumnSearchProps('address'),
            },
            {
                title: '联系方式',
                dataIndex: 'telephoneNumber',
                key: 'telephoneNumber',
                ...this.getColumnSearchProps('telephoneNumber'),
            },
            {
                title: '创建时间',
                dataIndex: 'createTime',
                key: 'createTime',
                ...this.getColumnSearchProps('createTime'),

            },
            {
                title: '修改时间',
                dataIndex: 'updateTime',
                key: 'updateTime',
                ...this.getColumnSearchProps('updateTime'),

            },
            {
                title: '修改人',
                dataIndex: 'updateName',
                key: 'updateName',
                ...this.getColumnSearchProps('updateName'),

            },
            {
                title: 'Action',
                dataIndex: '',
                key: 'x',
                render: (text) => <div>
                    <UpdateHospital data={text} refresh={()=>{this.onChange(this.page,this.pageSize)}}/>
                    <br/>
                    <DeleteHospital  id={text.id} refresh={()=>{this.onChange(this.page,this.pageSize)}}/>
                </div>,
            },
        ];
        return <div>
            <h3><b>医院详情</b></h3>
            <div style={{float:"right"}}><CreateHospital refresh={()=>{this.onChange(this.page,this.pageSize)}}/></div>
            <Table columns={columns}
                   size="small"
                   pagination={{defaultPageSize:5,
                       current:this.state.current,
                       onChange:this.onChange,total:this.state.total
                   }}
                   dataSource={this.state.data}/>
        </div>;
    }
}

export default HospitalTable;