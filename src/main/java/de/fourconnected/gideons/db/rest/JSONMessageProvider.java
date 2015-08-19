package de.fourconnected.gideons.db.rest;

import org.apache.commons.io.IOUtils;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * Created by david on 19.08.15.
 */
@Provider
@Produces("application/json")
@Consumes("application/json")
public class JSONMessageProvider implements MessageBodyReader<Object>,
        MessageBodyWriter<Object> {

    public Charset utf8;

    public JSONMessageProvider() {
        super();

        this.utf8 = Charset.forName("UTF-8");
    }

    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public Object readFrom(Class<Object> objectClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> stringStringMultivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, this.utf8);
        String jsonString = writer.toString();
        return GlobalGson.get().fromJson(jsonString, objectClass);
    }

    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public long getSize(Object o, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Object o, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> stringObjectMultivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        outputStream.write(GlobalGson.get().toJson(o).getBytes(this.utf8));
    }
}
