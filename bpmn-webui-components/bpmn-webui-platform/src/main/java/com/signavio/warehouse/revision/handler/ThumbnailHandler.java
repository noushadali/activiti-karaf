/**
 * Copyright (c) 2009, Signavio GmbH
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.signavio.warehouse.revision.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.activiti.karaf.bpmn.webui.platform.MyPNGTranscoder;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.PNGTranscoder;

import com.signavio.platform.annotations.HandlerConfiguration;
import com.signavio.platform.annotations.HandlerExportConfiguration;
import com.signavio.platform.annotations.HandlerMethodActivation;
import com.signavio.platform.security.business.FsSecureBusinessObject;
import com.signavio.warehouse.revision.business.RepresentationType;

@HandlerConfiguration(uri="/thumbnail", context=RevisionHandler.class, rel="exp")
@HandlerExportConfiguration(name="PNG Thumbnail Export", icon="/explorer/src/img/famfamfam/picture.png", mime="image/png")
public class ThumbnailHandler extends AbstractImageHandler {

	private float HEIGHT = 55f;
	private float WIDTH = 50f;
	
	public ThumbnailHandler(ServletContext servletContext) {
		super(servletContext);
		
	}

	@Override
	@HandlerMethodActivation
	public  <T extends FsSecureBusinessObject> byte[] doExport(T sbo, Object params){
		Map<TranscodingHints.Key, Object> hints = new HashMap<TranscodingHints.Key, Object>();
		
		hints.put(PNGTranscoder.KEY_MAX_WIDTH, WIDTH);
		hints.put(PNGTranscoder.KEY_MAX_HEIGHT, HEIGHT);
		
		return getImage(RepresentationType.PNG_SMALL, new MyPNGTranscoder(), sbo, hints);
	}
}
