import{r as s,C as T,u as y,j as _}from"./index-BIOZ3xfM.js";import{r as j,p as v,i as B,g as N}from"./index-DKUKv5_B.js";const $="_echarts_61qzd_1",q={echarts:$};function L({data:t,type:c,height:o,width:i,options:m,legend:p=!0,isMerge:g=!1,lazyUpdate:x=!1,onClick:f}){const u=s.useRef(null),r=s.useRef(null),n=T(),d=y().dir==="rtl",E=s.useMemo(()=>{const{series:e,xAxis:a}=t,{groupBy:l}=e?.[0]??{};return l||a||""},[t]);return s.useEffect(()=>{j(n,v())},[c,n]),s.useEffect(()=>{if(u.current!==null){const e=r.current=B(u.current,n);return()=>e.dispose()}},[n]),s.useEffect(()=>{const e=r.current;f&&e&&e.on("click",function(a){const{seriesName:l,data:h}=a||{},A=h?.raw?.find(R=>R[E]===l)??h?.raw?.[0];f(A?._original??A)})},[E,f]),s.useEffect(()=>{r.current&&i&&o&&r.current.resize({height:o,width:i})},[o,i]),s.useEffect(()=>{if(r.current){const e={...m};d&&e.yAxis&&(e.yAxis={...e.yAxis,position:"right"}),e.legend&&!Array.isArray(e.legend)&&(e.legend={...e.legend,padding:[8,32,8,32]}),r.current.setOption({...e,...!p&&{legend:void 0},color:N(c,t.config?.colors,t.config?.shades)},!g,x)}},[d,c,p,m,g,t.config,x]),_.jsx("div",{className:q.echarts,ref:u})}export{L as E};
