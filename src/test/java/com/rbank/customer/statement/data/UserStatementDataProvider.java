/**
 * 
 */
package com.rbank.customer.statement.data;

import java.util.ArrayList;
import java.util.List;

import com.rbank.customer.statement.model.RecordDetail;

/**
 * @author Michael Philomin Raj
 *
 */
public class UserStatementDataProvider {

	public static List<RecordDetail> getCsvData(){
		// Stubbing of recordDetails
		List<RecordDetail> recordDetails = new ArrayList<RecordDetail>();

		RecordDetail recordDetail1 = new RecordDetail();
		recordDetail1.setTransactionReference("177666");
		recordDetail1.setAccountNumber("NL93ABNA0585619023");
		recordDetail1.setDescription("Flowers for Rik Theuß");
		recordDetail1.setStartBalance("44.85");
		recordDetail1.setMutation("-22.24");
		recordDetail1.setEndBalance("22.61");
		recordDetails.add(recordDetail1);

		RecordDetail recordDetail2 = new RecordDetail();
		recordDetail2.setTransactionReference("112806");
		recordDetail2.setAccountNumber("NL69ABNA0433647324");
		recordDetail2.setDescription("Subscription for Jan Theußß");
		recordDetail2.setStartBalance("45.59");
		recordDetail2.setMutation("48.18");
		recordDetail2.setEndBalance("93.77");
		recordDetails.add(recordDetail2);

		RecordDetail recordDetail3 = new RecordDetail();
		recordDetail3.setTransactionReference("158338");
		recordDetail3.setAccountNumber("NL91RABO0315273637");
		recordDetail3.setDescription("Tickets for Vincent King");
		recordDetail3.setStartBalance("12.76");
		recordDetail3.setMutation("-39.5");
		recordDetail3.setEndBalance("-26.74");
		recordDetails.add(recordDetail3);

		RecordDetail recordDetail4 = new RecordDetail();
		recordDetail4.setTransactionReference("193499");
		recordDetail4.setAccountNumber("NL93ABNA0585619023");
		recordDetail4.setDescription("Candy for Daniël Dekker");
		recordDetail4.setStartBalance("88.44");
		recordDetail4.setMutation("-13.28");
		recordDetail4.setEndBalance("75.16");
		recordDetails.add(recordDetail4);

		RecordDetail recordDetail5 = new RecordDetail();
		recordDetail5.setTransactionReference("112806");
		recordDetail5.setAccountNumber("NL90ABNA0585647886");
		recordDetail5.setDescription("Clothes from Peter de Vries");
		recordDetail5.setStartBalance("32.76");
		recordDetail5.setMutation("49.03");
		recordDetail5.setEndBalance("81.79");
		recordDetails.add(recordDetail5);

		RecordDetail recordDetail6 = new RecordDetail();
		recordDetail6.setTransactionReference("112806");
		recordDetail6.setAccountNumber("NL91RABO0315273637");
		recordDetail6.setDescription("Tickets for Erik Dekker");
		recordDetail6.setStartBalance("41.63");
		recordDetail6.setMutation("12.41");
		recordDetail6.setEndBalance("54.04");
		recordDetails.add(recordDetail6);

		RecordDetail recordDetail7 = new RecordDetail();
		recordDetail7.setTransactionReference("108230");
		recordDetail7.setAccountNumber("NL32RABO0195610843");
		recordDetail7.setDescription("Flowers for Willem Bakker");
		recordDetail7.setStartBalance("43.63");
		recordDetail7.setMutation("-12.18");
		recordDetail7.setEndBalance("31.45");
		recordDetails.add(recordDetail7);

		RecordDetail recordDetail8 = new RecordDetail();
		recordDetail8.setTransactionReference("196213");
		recordDetail8.setAccountNumber("NL32RABO0195610843");
		recordDetail8.setDescription("Subscription from Rik de Vries");
		recordDetail8.setStartBalance("30.36");
		recordDetail8.setMutation("-35.1");
		recordDetail8.setEndBalance("-4.74");
		recordDetails.add(recordDetail8);

		RecordDetail recordDetail9 = new RecordDetail();
		recordDetail9.setTransactionReference("109762");
		recordDetail9.setAccountNumber("NL93ABNA0585619023");
		recordDetail9.setDescription("Flowers from Rik de Vries");
		recordDetail9.setStartBalance("47.45");
		recordDetail9.setMutation("17.82");
		recordDetail9.setEndBalance("65.27");
		recordDetails.add(recordDetail9);

		RecordDetail recordDetail10 = new RecordDetail();
		recordDetail10.setTransactionReference("163590");
		recordDetail10.setAccountNumber("NL27SNSB0917829871");
		recordDetail10.setDescription("Tickets from Rik Bakker");
		recordDetail10.setStartBalance("105.11");
		recordDetail10.setMutation("29.87");
		recordDetail10.setEndBalance("134.98");
		recordDetails.add(recordDetail10);
		return recordDetails;
	}

	public static List<RecordDetail> getXmLData(){

		List<RecordDetail> recordDetails = new ArrayList<RecordDetail>();
		RecordDetail recordDetail1 = new RecordDetail();
		recordDetail1.setTransactionReference("187997");
		recordDetail1.setAccountNumber("NL91RABO0315273637");
		recordDetail1.setDescription("Clothes for Rik King");
		recordDetail1.setStartBalance("57.6");
		recordDetail1.setMutation("-32.98");
		recordDetail1.setEndBalance("24.62");
		recordDetails.add(recordDetail1);

		RecordDetail recordDetail2 = new RecordDetail();
		recordDetail2.setTransactionReference("154270");
		recordDetail2.setAccountNumber("NL56RABO0149876948");
		recordDetail2.setDescription("Candy for Peter de Vries");
		recordDetail2.setStartBalance("5429");
		recordDetail2.setMutation("-939");
		recordDetail2.setEndBalance("6368");
		recordDetails.add(recordDetail2);

		RecordDetail recordDetail3 = new RecordDetail();
		recordDetail3.setTransactionReference("162197");
		recordDetail3.setAccountNumber("NL90ABNA0585647886");
		recordDetail3.setDescription("Tickets for Daniël de Vries");
		recordDetail3.setStartBalance("95.03");
		recordDetail3.setMutation("+48.33");
		recordDetail3.setEndBalance("143.36");
		recordDetails.add(recordDetail3);

		RecordDetail recordDetail4 = new RecordDetail();
		recordDetail4.setTransactionReference("129635");
		recordDetail4.setAccountNumber("NL27SNSB0917829871");
		recordDetail4.setDescription("Clothes for Vincent King");
		recordDetail4.setStartBalance("14.48");
		recordDetail4.setMutation("+16.39");
		recordDetail4.setEndBalance("30.87");
		recordDetails.add(recordDetail4);

		RecordDetail recordDetail5 = new RecordDetail();
		recordDetail5.setTransactionReference("148503");
		recordDetail5.setAccountNumber("NL93ABNA0585619023");
		recordDetail5.setDescription("Subscription from Willem Dekker");
		recordDetail5.setStartBalance("30.54");
		recordDetail5.setMutation("-13.18");
		recordDetail5.setEndBalance("17.36");
		recordDetails.add(recordDetail5);

		RecordDetail recordDetail6 = new RecordDetail();
		recordDetail6.setTransactionReference("163023");
		recordDetail6.setAccountNumber("NL43AEGO0773393871");
		recordDetail6.setDescription("Tickets for Daniël de Vries");
		recordDetail6.setStartBalance("37.79");
		recordDetail6.setMutation("-40.84");
		recordDetail6.setEndBalance("-3.05");
		recordDetails.add(recordDetail6);

		RecordDetail recordDetail7 = new RecordDetail();
		recordDetail7.setTransactionReference("162410");
		recordDetail7.setAccountNumber("NL69ABNA0433647324");
		recordDetail7.setDescription("Tickets from Jan Bakker");
		recordDetail7.setStartBalance("10.1");
		recordDetail7.setMutation("-0.3");
		recordDetail7.setEndBalance("9.8");
		recordDetails.add(recordDetail7);

		RecordDetail recordDetail8 = new RecordDetail();
		recordDetail8.setTransactionReference("112747");
		recordDetail8.setAccountNumber("NL56RABO0149876948");
		recordDetail8.setDescription("Candy from Jan Dekker");
		recordDetail8.setStartBalance("51.62");
		recordDetail8.setMutation("-42.36");
		recordDetail8.setEndBalance("9.26");
		recordDetails.add(recordDetail8);

		RecordDetail recordDetail9 = new RecordDetail();
		recordDetail9.setTransactionReference("140269");
		recordDetail9.setAccountNumber("NL43AEGO0773393871");
		recordDetail9.setDescription("Tickets for Vincent Dekker");
		recordDetail9.setStartBalance("3980");
		recordDetail9.setMutation("+1000");
		recordDetail9.setEndBalance("4981");
		recordDetails.add(recordDetail9);

		RecordDetail recordDetail10 = new RecordDetail();
		recordDetail10.setTransactionReference("115137");
		recordDetail10.setAccountNumber("NL43AEGO0773393871");
		recordDetail10.setDescription("Flowers for Jan Theuß");
		recordDetail10.setStartBalance("28.19");
		recordDetail10.setMutation("+3.22");
		recordDetail10.setEndBalance("31.41");
		recordDetails.add(recordDetail10);
		return recordDetails;
	}
}
