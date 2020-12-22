package com.abubusoft.kripton.retrofit2;

import com.abubusoft.kripton.BinderContext;
import okhttp3.MediaType;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;

public class AbstractConverter {
  protected final BinderContext binderContext;
  protected final Charset defaultCharset;
  protected final CodingErrorAction codingErrorAction;
  protected final MediaType mediaType;

  public AbstractConverter(BinderContext binderContext, Charset charset, CodingErrorAction codingErrorAction, boolean includeCharsetInContentType) {
    this.binderContext = binderContext;
    this.defaultCharset = charset;
    this.codingErrorAction = codingErrorAction;
    this.mediaType = buildMediaType(binderContext, charset, includeCharsetInContentType);
  }

  private MediaType buildMediaType(BinderContext binderContext, Charset charset, boolean includeCharsetInContentType) {
    String mediaTypeValue;
    switch (binderContext.getSupportedFormat()) {
      /** xml format. */
      case XML:
        mediaTypeValue = "application/xml";
        break;
      case CBOR:
        mediaTypeValue = "application/cbor";
        break;
      case YAML:
        mediaTypeValue = "text/yaml";
        break;
      case PROPERTIES:
        mediaTypeValue = "text/plain";
        break;
      case SMILE:
        mediaTypeValue = "application/x-jackson-smile";
        break;
      case JSON:
      default:
        mediaTypeValue = "application/json";
        break;
    }

    return MediaType.parse(mediaTypeValue + (includeCharsetInContentType ? "; charset=" + charset.name() : ""));
  }

  protected CharsetDecoder createCharsetDecoder(MediaType mediaType) {
    CharsetDecoder decoder;

    try {
      if (mediaType == null || mediaType.charset() == null) {
        decoder = defaultCharset.newDecoder();
      } else {
        decoder = mediaType.charset().newDecoder();
      }
    } catch (Throwable e) {
      decoder = defaultCharset.newDecoder();
    }
    decoder.onMalformedInput(this.codingErrorAction);
    decoder.onUnmappableCharacter(this.codingErrorAction);
    return decoder;
  }
}
