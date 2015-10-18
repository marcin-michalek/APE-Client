package pl.michalek.marcin.apeclient.network.response;

import pl.michalek.marcin.apeclient.ape.protocol.Raw;
import pl.michalek.marcin.apeclient.network.data.Data;

/**
 * Base APE server single response.
 *
 * @author Marcin Michałek
 */
public class BaseResponse{
    public Long time;
    public Raw raw;
    public Data data;
}
