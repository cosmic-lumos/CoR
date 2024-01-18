import React, { useRef, useState, useEffect } from 'react';
import { Wheel } from 'react-custom-roulette';
// import Roulette from './roulette';
import './App.css'
import './roulette.css'
import * as StompJs from '@stomp/stompjs';



const App: React.FC = () => {



  //////////////////////////////  STOMP  /////////////////////////////

  let [stompClient, setStompClient] = useState(null);
  const [room, setRoom] = useState({});

  const [sessionId, setSessionId] = useState(null);
  
  function extractMessageId(str: string) {
    const regex = /message-id:([^\n]+)/;
    const match = str.match(regex);
    if(match == null){
      return null;
    }
    let lastIndex = match[1].trim().lastIndexOf('-');
    let modifiedString = match[1].trim().substring(0, lastIndex);
  
    return modifiedString;
  }

  useEffect(()=>{
    if(userlist == null){
      return;
    }
    userlist.map((item: any) => {
      if(item.sessionId == sessionId){
        setUser(item.name);
      }
    })
  }, [sessionId])

  const connect = () => {
    const client = new StompJs.Client({
      brokerURL: 'ws://10.255.81.70:8028/gs-guide-websocket',
      debug: function (str) {
        const sId = extractMessageId(str);
        if(sId != null){
          setSessionId(sId);
        }
      },
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
    });

    client.onConnect = function (frame) {
      console.log('Connected: ' + frame);
      client.subscribe('/topic/room', (room) => {
        setdata(Object.keys(JSON.parse(room.body).categories).map((item, index) => {
            return { option: item, id: index};
        }))
        setUserlist(JSON.parse(room.body).users);
      });
      client.publish({
        destination: "/app/enter",
      })
      client.subscribe('/topic/room/result', (result) => {
        setPrizeNumber(JSON.parse(result.body).prizeNumber);
        setMustSpin(true);
      })
    }

    client.onWebSocketError = (error) => {
      console.error('!Error with websocket', error);
    };

    client.onStompError = (frame) => {
      console.error('Broker reported error: ' + frame.headers['message']);
      console.error('Additional details: ' + frame.body);
    };

    client.activate();

    setStompClient(client);
    console.log('연결됨')



  }

  const disConnect = () => {
    // 연결 끊기
    if (stompClient === null) {
      return;
    }
    stompClient.deactivate();
    console.log('연결 끊음')
  };

  useEffect(() => {
    connect();

    return () => disConnect();
  }, [])

////////////////////////////////////////////////////////////


  let [mustSpin, setMustSpin] = useState(false);
  let [prizeNumber, setPrizeNumber] = useState(0);

  const handleSpinClick = () => {
    // const newPrizeNumber = Math.floor(Math.random() * data.length);
    // setstart(false)
    stompClient.publish({
      destination: "/app/selectNumber",
    })
  };

  const [data,setdata] = useState([ { option: '피자' , id : 0},
                                    { option: '짬뽕' , id : 1} ]);
  const [userlist,setUserlist] = useState (null);
  const [user, setUser] = useState('귀여운 무지')
  const [inputs, setInputs] = useState({ option:'' });
  const { option } = inputs;
  const onChange = (e: React.ChangeEvent<any>) => {
    const { name, value } = e.target;
    setInputs({
      ...inputs,
      [name]: value
    });
    console.log(inputs.option)

  };

  const nextId = useRef(2);


  const onCreate = () => {
    // const menu = {
    //   id: nextId.current,
    //   option
    // };
    // setdata([...data, menu]);
    // setInputs({
    //   option: ''
    // });
    nextId.current += 1;
    console.log(JSON.stringify({ 'name' : inputs.option}))

    stompClient.publish({
      destination: "/app/addCategory",
      body: JSON.stringify({ 'name' : inputs.option})
    })

  }
  const onRemove = (id: number) => {
    if (data.length <= 2){
      alert('최소한 2개의 옵션이 필요합니다')
    }
    else {
      // console.log( data[id].option+ '지움')
      // // user.id 가 파라미터로 일치하지 않는 원소만 추출해서 새로운 배열을 만듬
      // // = user.id 가 id 인 것을 제거함
      // setdata(data.filter(menu => menu.id !== id));
      // console.log( data + '지움완료')
      stompClient.publish({
        destination: "/app/removeCategory",
        body: JSON.stringify({ 'name' : data[id].option })
      })
    }
  }
  // let result:string = null
  // const [result,setresult] = useState("");
  // console.log(start)
  // if (!mustSpin && !start){
  //   result=data[prizeNumber].option
  // }
  // else {
  //   result = null
  // }


  return (
    <div className='main'>
      <div className='title'> Co-Roulette</div>
      <div className='game_container'>
        {/* <Roulette data ={data}/> */}
        {/* <Roulette/> */}
        <div className='roulette_container'>
          <div className='roulette_result'>
            {
              // !mustSpin ? 
              //   prizeNumber == -1 ?
              //       null 
              //         : 
              //       result
              //       : 
              //       null
              // result
              !mustSpin ? data[prizeNumber].option : null
            }

          </div>
          <div className='roulette'>
              <Wheel
                  mustStartSpinning={mustSpin}
                  prizeNumber={prizeNumber}
                  data={data}
                  onStopSpinning={() => {
                  setMustSpin(false);
                  }}
                  backgroundColors={['#4BC8FA', '#3EA2FA', '#4A8FFA', '#2DDBFA']}
                  textColors={['#ffffff']}
                  outerBorderColor={'#eeeeee'}
                  outerBorderWidth={10}
                  radiusLineColor={'#eeeeee'}
                  radiusLineWidth={10}
                  spinDuration={0.5}
              />
          </div>
          <div className='spin_btn' onClick={handleSpinClick}>SPIN</div>
        </div>
        <hr className="divider"/>
        <div className='roulette_info_container'>
          <div className='add_category_container'>
            <div className='add_category_title'>{user}님, <br/> 카테고리를 추가하세요</div>
            <div className='add_category_container2'>
              <input className='add_category_input' placeholder='  카테고리 입력' 
                      onChange={onChange}
                      name='option'
                      value={option}></input>
              <div className='add_category_btn' onClick={onCreate}> 추가 </div>
            </div>
          </div>
          <div className='category_list_container'>
            <div className='category_list_title'> 카테고리 </div>
            <div className='category_list_box'>
            { 
              data.map(function(a, i){
                return (
                <div className="category_list_text">
                  <div>{ data[i].option }</div>
                  <div onClick={() => onRemove(i)} className='deletebtn'> 지우기 </div>
                </div> )
              }) 
            }
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;




