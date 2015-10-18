package pl.michalek.marcin.apeclient.network.data;

import java.util.ArrayList;
import java.util.List;

/**
 * As all requests to APE server must be JSON arrays,
 * this factory returns request wrapped by ArrayList.
 *
 * @author Marcin Michałek
 */
public class RequestDataFactory {
  public static List<BaseAPERequestData> createRequestDataList(BaseAPERequestData apeRequestData){
    ArrayList<BaseAPERequestData> apeRequestDataList = new ArrayList<BaseAPERequestData>();
    apeRequestDataList.add(apeRequestData);
    return apeRequestDataList;
  }
}
