import{R as S,z as h,j as s,I as G,D as V,r as i,a4 as A,a5 as B,t as _,a6 as M,k,n as D,a7 as F,a8 as K,a9 as L,aa as U,ab as O,h as z,i as Y,ac as Z,Z as q,Y as J,v as P,B as I}from"./index-BIOZ3xfM.js";const Q=S.forwardRef(function({icon:t,label:e,tag:d,up:u,"tag-css":b,percent:g,value:f,eval:r,context:j},p){const m=S.useRef(),y=r(f),v=r(g),C=r(d),E=r(b),c=r(u);function x(a){return isNaN(a)?a:j.__number(a)}function R(){let a;return c==null?a="info":a=c?"success":"error","text-"+a}return S.useEffect(()=>{const a=m.current;if(a){const l=a?.parentElement?.parentElement;if(l){const n=h("report-box");return l.classList.add(n),()=>l.classList.remove(n)}}},[]),s.jsxs("div",{ref:m,className:h("report-data","hidden"),children:[t&&t.includes("fa-")&&s.jsx("i",{className:h("report-icon","fa",t)}),t&&!t.includes("fa-")&&s.jsx(G,{icon:t,className:h("report-icon")}),s.jsxs("div",{children:[s.jsx("h1",{children:x(y)}),s.jsx("small",{children:V.get(e)}),s.jsxs("div",{className:h("report-percent","font-bold","pull-right",R()),children:[s.jsx("span",{children:j.__percent(v)}),c!=null&&s.jsx(G,{icon:"trending_"+(c?"up":"down")})]})]}),C&&s.jsx("div",{className:h("report-tags"),children:s.jsx("span",{className:h("label",E),children:C})})]})});function H(o,t,e){if(o.translatable&&_(o.type)==="string"){const d="value:"+t,u=V.get(d);if(u!==d)return{...e,["$t:"+o.name]:u}}return e}function W(o,t,e){return L(t,{props:o,context:H(o,t,e)})}function X(o,t){const e=U(t,o);return O(o,H(o,e,t))}const $=i.forwardRef(function({context:t,columns:e,sums:d,view:u},b){const[g,f]=A(),r=i.useMemo(()=>t.data||[],[t]),j=i.useCallback(()=>{},[]),p=i.useMemo(()=>{const[c]=r||[];return Object.keys(c||{}).filter(x=>x!=="$$hashKey")},[r]),{view:m,fields:y}=i.useMemo(()=>{const c=(d||"").split(/\s*,\s*/),x=e?e.split(/\s*,\s*/):p,R={},a=[];return x.forEach(l=>{const n=u?.items?.find(w=>w.name===l)??{};if(n&&!n.hidden){const w=Object.assign({},n,n.widgetAttrs,{name:l,title:n.title||n.autoTitle||B(l),type:_(n.serverType||"STRING").toUpperCase(),serverType:_(n.serverType||"STRING").toUpperCase(),...c.includes(l)&&{aggregate:"sum"}});R[l]=w,a.push(w)}}),{fields:R,view:{...u,type:"grid",items:a}}},[d,e,u,p]),v=i.useCallback(async()=>{const c=m,{items:x=[]}=c;let a="data:text/csv;charset=utf-8,"+x.map(n=>n.title).join(";")+`
`;r.forEach(n=>{const w=x.map(N=>N.name).map(function(N){let T=N&&n[N];return T==null&&(T=""),'"'+(""+T).replace(/"/g,'""')+'"'});a+=w.join(";")+`
`});const l=(c.title||"export").toLowerCase().replace(/ /g,"_");M(encodeURI(a),l+".csv")},[r,m]),C=k(D());i.useEffect(()=>{C(c=>({...c,view:m,onExport:v}))},[m,C,v]);const E=F(y,X);return s.jsx(K,{showEditIcon:!1,records:r,aggregationType:"all",view:m,fields:y,state:g,setState:f,sortHandler:E,allowCheckboxSelection:!1,columnFormatter:W,onSearch:j,noRecordsText:V.get("No records found.")})}),ee="_container_39894_1",te={container:ee};function ae({meta:o}){const{dashlet:t}=z(),{view:e}=o,[d,u]=i.useState({}),b=k(D()),g=Y(),f=i.useCallback(async()=>{if(e.name){const p=await Z(e.name,g());u(p)}},[g,e]);q(async()=>{await f()},[f]),i.useEffect(()=>{t&&b({view:e,onRefresh:f})},[t,e,f,b]);const r=J(e.template),j=i.useMemo(()=>({components:{"report-box":p=>s.jsx(Q,{...p}),"report-table":p=>s.jsx($,{...p,view:e})}}),[e]);return P("custom",f),s.jsx(I,{d:"flex",flexGrow:1,className:te.container,children:s.jsx(I,{d:"flex",className:h(e.css),flexGrow:1,children:s.jsx(r,{context:d,options:j})})})}export{ae as Custom};
