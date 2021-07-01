package com.codingtest.wirebarley.service;

import com.codingtest.wirebarley.domain.RatioResult;
import com.codingtest.wirebarley.dto.response.CalculatorResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
@Log4j2
public class CalculatorService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${currencylayer.api.url}")
    private String URL;

    @Value("${currencylayer.api.access_key}")
    private String ACCESSKEY;

    public CalculatorResponseDto getExchangeResult(String nation, String source) {
        log.info("nation:::::::" + nation);

        String exchangeRate = getExchangeRate(source + nation);

        CalculatorResponseDto calculatorResponseDto = CalculatorResponseDto.builder()
                .nation(nation)
                .source(source)
                .exchangeRate(exchangeRate)
                .build();

        return calculatorResponseDto;
    }

    public String getExchangeRate(String nation) {
        log.info("nation::::::" + nation);
        String exchangeRate = String.format("%.2f", getRatioResult().getQuotes().get(nation));
        log.info("ratio::::::" + exchangeRate);

        return exchangeRate;
    }

    public RatioResult getRatioResult() {
        log.info("URL:::::" + URL);
        log.info("ACCESSKEY:::::" + ACCESSKEY);
        String requestUrl = "";
        try {
            requestUrl = String.format(URL + "?access_key=%s", URLEncoder.encode(ACCESSKEY, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.info("requestUrl::::::" + requestUrl);
        RatioResult result = restTemplate.getForObject(requestUrl, RatioResult.class);
//        String temp = "{  \n" +
//                "    \"success\":true,\n" +
//                "    \"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\n" +
//                "    \"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\n" +
//                "    \"timestamp\":1545881647,\n" +
//                "    \"source\":\"USD\",\n" +
//                "    \"quotes\":{  \n" +
//                "        \"USDAED\":3.673197,\n" +
//                "        \"USDAFN\":76.088502,\n" +
//                "        \"USDALL\":108.014949,\n" +
//                "        \"USDAMD\":484.684999,\n" +
//                "        \"USDANG\":1.78935,\n" +
//                "        \"USDAOA\":308.428019,\n" +
//                "        \"USDARS\":38.025498,\n" +
//                "        \"USDAUD\":1.41645,\n" +
//                "        \"USDAWG\":1.8005,\n" +
//                "        \"USDAZN\":1.704992,\n" +
//                "        \"USDBAM\":1.720195,\n" +
//                "        \"USDBBD\":2.0065,\n" +
//                "        \"USDBDT\":84.095497,\n" +
//                "        \"USDBGN\":1.720202,\n" +
//                "        \"USDBHD\":0.377845,\n" +
//                "        \"USDBIF\":1796.55,\n" +
//                "        \"USDBMD\":1,\n" +
//                "        \"USDBND\":1.375902,\n" +
//                "        \"USDBOB\":6.926599,\n" +
//                "        \"USDBRL\":3.921797,\n" +
//                "        \"USDBSD\":1.002425,\n" +
//                "        \"USDBTC\":0.000264,\n" +
//                "        \"USDBTN\":70.202655,\n" +
//                "        \"USDBWP\":10.813986,\n" +
//                "        \"USDBYN\":2.143987,\n" +
//                "        \"USDBYR\":19600,\n" +
//                "        \"USDBZD\":2.02065,\n" +
//                "        \"USDCAD\":1.359085,\n" +
//                "        \"USDCDF\":1631.000242,\n" +
//                "        \"USDCHF\":0.992601,\n" +
//                "        \"USDCLF\":0.025048,\n" +
//                "        \"USDCLP\":693.601955,\n" +
//                "        \"USDCNY\":6.891597,\n" +
//                "        \"USDCOP\":3302.1,\n" +
//                "        \"USDCRC\":600.159866,\n" +
//                "        \"USDCUC\":1,\n" +
//                "        \"USDCUP\":26.5,\n" +
//                "        \"USDCVE\":96.98802,\n" +
//                "        \"USDCZK\":22.743597,\n" +
//                "        \"USDDJF\":177.720369,\n" +
//                "        \"USDDKK\":6.55895,\n" +
//                "        \"USDDOP\":50.863502,\n" +
//                "        \"USDDZD\":118.89004,\n" +
//                "        \"USDEGP\":17.935975,\n" +
//                "        \"USDERN\":15.000356,\n" +
//                "        \"USDETB\":28.186971,\n" +
//                "        \"USDEUR\":0.878695,\n" +
//                "        \"USDFJD\":2.134978,\n" +
//                "        \"USDFKP\":0.790015,\n" +
//                "        \"USDGBP\":0.790055,\n" +
//                "        \"USDGEL\":2.664971,\n" +
//                "        \"USDGGP\":0.790081,\n" +
//                "        \"USDGHS\":4.92205,\n" +
//                "        \"USDGIP\":0.790015,\n" +
//                "        \"USDGMD\":49.349825,\n" +
//                "        \"USDGNF\":9119.750355,\n" +
//                "        \"USDGTQ\":7.753973,\n" +
//                "        \"USDGYD\":209.405028,\n" +
//                "        \"USDHKD\":7.83205,\n" +
//                "        \"USDHNL\":24.452983,\n" +
//                "        \"USDHRK\":6.528297,\n" +
//                "        \"USDHTG\":77.535503,\n" +
//                "        \"USDHUF\":282.139656,\n" +
//                "        \"USDIDR\":14562.5,\n" +
//                "        \"USDILS\":3.77795,\n" +
//                "        \"USDIMP\":0.790081,\n" +
//                "        \"USDINR\":70.255029,\n" +
//                "        \"USDIQD\":1196.15,\n" +
//                "        \"USDIRR\":42105.000352,\n" +
//                "        \"USDISK\":117.039989,\n" +
//                "        \"USDJEP\":0.790081,\n" +
//                "        \"USDJMD\":128.584974,\n" +
//                "        \"USDJOD\":0.71021,\n" +
//                "        \"USDJPY\":110.959498,\n" +
//                "        \"USDKES\":102.009392,\n" +
//                "        \"USDKGS\":69.850079,\n" +
//                "        \"USDKHR\":4034.850439,\n" +
//                "        \"USDKMF\":433.624965,\n" +
//                "        \"USDKPW\":900.056691,\n" +
//                "        \"USDKRW\":1121.419945,\n" +
//                "        \"USDKWD\":0.30395,\n" +
//                "        \"USDKYD\":0.835385,\n" +
//                "        \"USDKZT\":371.980321,\n" +
//                "        \"USDLAK\":8577.350421,\n" +
//                "        \"USDLBP\":1511.700215,\n" +
//                "        \"USDLKR\":181.995016,\n" +
//                "        \"USDLRD\":157.49779,\n" +
//                "        \"USDLSL\":14.580079,\n" +
//                "        \"USDLTL\":2.95274,\n" +
//                "        \"USDLVL\":0.60489,\n" +
//                "        \"USDLYD\":1.398302,\n" +
//                "        \"USDMAD\":9.57215,\n" +
//                "        \"USDMDL\":17.241499,\n" +
//                "        \"USDMGA\":3546.103567,\n" +
//                "        \"USDMKD\":54.068501,\n" +
//                "        \"USDMMK\":1570.350024,\n" +
//                "        \"USDMNT\":2637.254916,\n" +
//                "        \"USDMOP\":8.087899,\n" +
//                "        \"USDMRO\":356.999895,\n" +
//                "        \"USDMUR\":34.397439,\n" +
//                "        \"USDMVR\":15.449742,\n" +
//                "        \"USDMWK\":730.9697,\n" +
//                "        \"USDMXN\":19.894202,\n" +
//                "        \"USDMYR\":4.176501,\n" +
//                "        \"USDMZN\":61.490214,\n" +
//                "        \"USDNAD\":14.579558,\n" +
//                "        \"USDNGN\":365.14006,\n" +
//                "        \"USDNIO\":32.539886,\n" +
//                "        \"USDNOK\":8.74611,\n" +
//                "        \"USDNPR\":112.505037,\n" +
//                "        \"USDNZD\":1.48712,\n" +
//                "        \"USDOMR\":0.38613,\n" +
//                "        \"USDPAB\":1.002415,\n" +
//                "        \"USDPEN\":3.3677,\n" +
//                "        \"USDPGK\":3.37735,\n" +
//                "        \"USDPHP\":52.72027,\n" +
//                "        \"USDPKR\":140.215019,\n" +
//                "        \"USDPLN\":3.76165,\n" +
//                "        \"USDPYG\":5935.90292,\n" +
//                "        \"USDQAR\":3.641105,\n" +
//                "        \"USDRON\":4.073703,\n" +
//                "        \"USDRSD\":103.902084,\n" +
//                "        \"USDRUB\":68.953405,\n" +
//                "        \"USDRWF\":895.105,\n" +
//                "        \"USDSAR\":3.760902,\n" +
//                "        \"USDSBD\":8.20555,\n" +
//                "        \"USDSCR\":13.683499,\n" +
//                "        \"USDSDG\":47.736004,\n" +
//                "        \"USDSEK\":9.066405,\n" +
//                "        \"USDSGD\":1.37275,\n" +
//                "        \"USDSHP\":1.320899,\n" +
//                "        \"USDSLL\":8599.9997,\n" +
//                "        \"USDSOS\":580.000195,\n" +
//                "        \"USDSRD\":7.457965,\n" +
//                "        \"USDSTD\":21050.59961,\n" +
//                "        \"USDSVC\":8.770703,\n" +
//                "        \"USDSYP\":514.999392,\n" +
//                "        \"USDSZL\":14.583503,\n" +
//                "        \"USDTHB\":32.607967,\n" +
//                "        \"USDTJS\":9.44805,\n" +
//                "        \"USDTMT\":3.5,\n" +
//                "        \"USDTND\":3.00425,\n" +
//                "        \"USDTOP\":2.25965,\n" +
//                "        \"USDTRY\":5.277175,\n" +
//                "        \"USDTTD\":6.794603,\n" +
//                "        \"USDTWD\":30.796503,\n" +
//                "        \"USDTZS\":2299.901063,\n" +
//                "        \"USDUAH\":27.443502,\n" +
//                "        \"USDUGX\":3716.096617,\n" +
//                "        \"USDUSD\":1,\n" +
//                "        \"USDUYU\":32.344501,\n" +
//                "        \"USDUZS\":8350.549968,\n" +
//                "        \"USDVEF\":9.987501,\n" +
//                "        \"USDVND\":23336.2,\n" +
//                "        \"USDVUV\":113.783789,\n" +
//                "        \"USDWST\":2.626846,\n" +
//                "        \"USDXAF\":576.94015,\n" +
//                "        \"USDXAG\":0.06652,\n" +
//                "        \"USDXAU\":0.000787,\n" +
//                "        \"USDXCD\":2.70255,\n" +
//                "        \"USDXDR\":0.721204,\n" +
//                "        \"USDXOF\":576.940096,\n" +
//                "        \"USDXPF\":104.903214,\n" +
//                "        \"USDYER\":250.349931,\n" +
//                "        \"USDZAR\":14.523899,\n" +
//                "        \"USDZMK\":9001.202279,\n" +
//                "        \"USDZMW\":11.953972,\n" +
//                "        \"USDZWL\":322.355011\n" +
//                "    }\n" +
//                "}";
//        RatioResult result = null;
//        try {
//            result = new ObjectMapper().readValue(temp, RatioResult.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

        return result;
    }

}
