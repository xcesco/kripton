/**
 * 
 */
package bind.generichierarchy.case1.transfer;

import com.abubusoft.kripton.annotation.BindType;

import bind.generichierarchy.case1.model.Message;

@BindType
public class MessageListResponse extends RestListEntity<Message> {

	private static final long serialVersionUID = 551529343234347332L;

}
