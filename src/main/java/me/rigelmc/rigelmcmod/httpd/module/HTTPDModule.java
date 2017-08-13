package me.rigelmc.rigelmcmod.httpd.module;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import me.rigelmc.rigelmcmod.RigelMCMod;
import me.rigelmc.rigelmcmod.httpd.HTTPDPageBuilder;
import me.rigelmc.rigelmcmod.httpd.NanoHTTPD;
import me.rigelmc.rigelmcmod.httpd.NanoHTTPD.HTTPSession;
import me.rigelmc.rigelmcmod.httpd.NanoHTTPD.Method;
import me.rigelmc.rigelmcmod.httpd.NanoHTTPD.Response;
import me.rigelmc.rigelmcmod.util.FLog;
import net.pravian.aero.component.PluginComponent;

public abstract class HTTPDModule extends PluginComponent<RigelMCMod>
{

    protected final String uri;
    protected final Method method;
    protected final Map<String, String> headers;
    protected final Map<String, String> params;
    protected final Socket socket;
    protected final HTTPSession session;

    public HTTPDModule(RigelMCMod plugin, HTTPSession session)
    {
        super(plugin);
        this.uri = session.getUri();
        this.method = session.getMethod();
        this.headers = session.getHeaders();
        this.params = session.getParms();
        this.socket = session.getSocket();
        this.session = session;
    }

    public String getBody()
    {
        return null;
    }

    public String getTitle()
    {
        return null;
    }

    public String getStyle()
    {
        return null;
    }

    public String getScript()
    {
        return null;
    }

    public Response getResponse()
    {
        return new HTTPDPageBuilder(getBody(), getTitle(), getStyle(), getScript()).getResponse();
    }

    protected final Map<String, String> getFiles()
    {
        Map<String, String> files = new HashMap<>();

        try
        {
            session.parseBody(files);
        }
        catch (IOException | NanoHTTPD.ResponseException ex)
        {
            FLog.severe(ex);
        }

        return files;
    }
}
