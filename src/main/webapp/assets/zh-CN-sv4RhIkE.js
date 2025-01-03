import{d as s,A as i,K as m,M as o}from"./index-BIOZ3xfM.js";import{i as u}from"./isSameWeek-CHl6ZweT.js";const c={lessThanXSeconds:{one:"不到 1 秒",other:"不到 {{count}} 秒"},xSeconds:{one:"1 秒",other:"{{count}} 秒"},halfAMinute:"半分钟",lessThanXMinutes:{one:"不到 1 分钟",other:"不到 {{count}} 分钟"},xMinutes:{one:"1 分钟",other:"{{count}} 分钟"},xHours:{one:"1 小时",other:"{{count}} 小时"},aboutXHours:{one:"大约 1 小时",other:"大约 {{count}} 小时"},xDays:{one:"1 天",other:"{{count}} 天"},aboutXWeeks:{one:"大约 1 个星期",other:"大约 {{count}} 个星期"},xWeeks:{one:"1 个星期",other:"{{count}} 个星期"},aboutXMonths:{one:"大约 1 个月",other:"大约 {{count}} 个月"},xMonths:{one:"1 个月",other:"{{count}} 个月"},aboutXYears:{one:"大约 1 年",other:"大约 {{count}} 年"},xYears:{one:"1 年",other:"{{count}} 年"},overXYears:{one:"超过 1 年",other:"超过 {{count}} 年"},almostXYears:{one:"将近 1 年",other:"将近 {{count}} 年"}},h=(t,n,e)=>{let a;const r=c[t];return typeof r=="string"?a=r:n===1?a=r.one:a=r.other.replace("{{count}}",String(n)),e?.addSuffix?e.comparison&&e.comparison>0?a+"内":a+"前":a},l={full:"y'年'M'月'd'日' EEEE",long:"y'年'M'月'd'日'",medium:"yyyy-MM-dd",short:"yy-MM-dd"},f={full:"zzzz a h:mm:ss",long:"z a h:mm:ss",medium:"a h:mm:ss",short:"a h:mm"},g={full:"{{date}} {{time}}",long:"{{date}} {{time}}",medium:"{{date}} {{time}}",short:"{{date}} {{time}}"},P={date:s({formats:l,defaultWidth:"full"}),time:s({formats:f,defaultWidth:"full"}),dateTime:s({formats:g,defaultWidth:"full"})};function d(t,n,e){const a="eeee p";return u(t,n,e)?a:t.getTime()>n.getTime()?"'下个'"+a:"'上个'"+a}const b={lastWeek:d,yesterday:"'昨天' p",today:"'今天' p",tomorrow:"'明天' p",nextWeek:d,other:"PP p"},y=(t,n,e,a)=>{const r=b[t];return typeof r=="function"?r(n,e,a):r},w={narrow:["前","公元"],abbreviated:["前","公元"],wide:["公元前","公元"]},p={narrow:["1","2","3","4"],abbreviated:["第一季","第二季","第三季","第四季"],wide:["第一季度","第二季度","第三季度","第四季度"]},v={narrow:["一","二","三","四","五","六","七","八","九","十","十一","十二"],abbreviated:["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"],wide:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]},W={narrow:["日","一","二","三","四","五","六"],short:["日","一","二","三","四","五","六"],abbreviated:["周日","周一","周二","周三","周四","周五","周六"],wide:["星期日","星期一","星期二","星期三","星期四","星期五","星期六"]},M={narrow:{am:"上",pm:"下",midnight:"凌晨",noon:"午",morning:"早",afternoon:"下午",evening:"晚",night:"夜"},abbreviated:{am:"上午",pm:"下午",midnight:"凌晨",noon:"中午",morning:"早晨",afternoon:"中午",evening:"晚上",night:"夜间"},wide:{am:"上午",pm:"下午",midnight:"凌晨",noon:"中午",morning:"早晨",afternoon:"中午",evening:"晚上",night:"夜间"}},k={narrow:{am:"上",pm:"下",midnight:"凌晨",noon:"午",morning:"早",afternoon:"下午",evening:"晚",night:"夜"},abbreviated:{am:"上午",pm:"下午",midnight:"凌晨",noon:"中午",morning:"早晨",afternoon:"中午",evening:"晚上",night:"夜间"},wide:{am:"上午",pm:"下午",midnight:"凌晨",noon:"中午",morning:"早晨",afternoon:"中午",evening:"晚上",night:"夜间"}},S=(t,n)=>{const e=Number(t);switch(n?.unit){case"date":return e.toString()+"日";case"hour":return e.toString()+"时";case"minute":return e.toString()+"分";case"second":return e.toString()+"秒";default:return"第 "+e.toString()}},x={ordinalNumber:S,era:i({values:w,defaultWidth:"wide"}),quarter:i({values:p,defaultWidth:"wide",argumentCallback:t=>t-1}),month:i({values:v,defaultWidth:"wide"}),day:i({values:W,defaultWidth:"wide"}),dayPeriod:i({values:M,defaultWidth:"wide",formattingValues:k,defaultFormattingWidth:"wide"})},z=/^(第\s*)?\d+(日|时|分|秒)?/i,F=/\d+/i,D={narrow:/^(前)/i,abbreviated:/^(前)/i,wide:/^(公元前|公元)/i},V={any:[/^(前)/i,/^(公元)/i]},X={narrow:/^[1234]/i,abbreviated:/^第[一二三四]刻/i,wide:/^第[一二三四]刻钟/i},N={any:[/(1|一)/i,/(2|二)/i,/(3|三)/i,/(4|四)/i]},C={narrow:/^(一|二|三|四|五|六|七|八|九|十[二一])/i,abbreviated:/^(一|二|三|四|五|六|七|八|九|十[二一]|\d|1[12])月/i,wide:/^(一|二|三|四|五|六|七|八|九|十[二一])月/i},E={narrow:[/^一/i,/^二/i,/^三/i,/^四/i,/^五/i,/^六/i,/^七/i,/^八/i,/^九/i,/^十(?!(一|二))/i,/^十一/i,/^十二/i],any:[/^一|1/i,/^二|2/i,/^三|3/i,/^四|4/i,/^五|5/i,/^六|6/i,/^七|7/i,/^八|8/i,/^九|9/i,/^十(?!(一|二))|10/i,/^十一|11/i,/^十二|12/i]},L={narrow:/^[一二三四五六日]/i,short:/^[一二三四五六日]/i,abbreviated:/^周[一二三四五六日]/i,wide:/^星期[一二三四五六日]/i},T={any:[/日/i,/一/i,/二/i,/三/i,/四/i,/五/i,/六/i]},Y={any:/^(上午?|下午?|午夜|[中正]午|早上?|下午|晚上?|凌晨|)/i},q={any:{am:/^上午?/i,pm:/^下午?/i,midnight:/^午夜/i,noon:/^[中正]午/i,morning:/^早上/i,afternoon:/^下午/i,evening:/^晚上?/i,night:/^凌晨/i}},O={ordinalNumber:m({matchPattern:z,parsePattern:F,valueCallback:t=>parseInt(t,10)}),era:o({matchPatterns:D,defaultMatchWidth:"wide",parsePatterns:V,defaultParseWidth:"any"}),quarter:o({matchPatterns:X,defaultMatchWidth:"wide",parsePatterns:N,defaultParseWidth:"any",valueCallback:t=>t+1}),month:o({matchPatterns:C,defaultMatchWidth:"wide",parsePatterns:E,defaultParseWidth:"any"}),day:o({matchPatterns:L,defaultMatchWidth:"wide",parsePatterns:T,defaultParseWidth:"any"}),dayPeriod:o({matchPatterns:Y,defaultMatchWidth:"any",parsePatterns:q,defaultParseWidth:"any"})},H={code:"zh-CN",formatDistance:h,formatLong:P,formatRelative:y,localize:x,match:O,options:{weekStartsOn:1,firstWeekContainsDate:4}};export{H as default,H as zhCN};
