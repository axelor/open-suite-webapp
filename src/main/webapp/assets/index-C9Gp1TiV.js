import{r,p as y,j as x}from"./index-BIOZ3xfM.js";import{b as f,a as b}from"./index-DKUKv5_B.js";import{E as g}from"./echarts-CRAeQ7K3.js";const d={legend:{bottom:5,type:"scroll"},tooltip:{trigger:"axis"},xAxis:{type:"category",boundaryGap:!1},yAxis:{type:"value"},series:[]};function i({data:o,type:a,...p}){const[l,n]=r.useState(d);return r.useEffect(()=>{const{data:s,types:u,formatter:m}=f(o);n(y(t=>{b(t,o),t.series=s.map(e=>({name:e.key,type:"line",data:e.values.map(({y:c})=>c),...a==="area"?{areaStyle:{}}:{}})),t.xAxis.data=u,t.legend.data=s.map(e=>e.key).filter(e=>e),t.tooltip.valueFormatter=m}))},[a,o]),x.jsx(g,{options:l,data:o,...p})}const S=Object.freeze(Object.defineProperty({__proto__:null,Line:i},Symbol.toStringTag,{value:"Module"})),v=Object.freeze(Object.defineProperty({__proto__:null,Area:i},Symbol.toStringTag,{value:"Module"}));export{v as a,S as i};
