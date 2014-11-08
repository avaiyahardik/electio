/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import DAO.DBDAOImplCandidate;
import DAO.DBDAOImplElection;
import DAO.DBDAOImplNominee;
import DAO.DBDAOImplProbableNominee;
import DAO.DBDAOImplVoter;
import Model.Candidate;
import Model.Election;
import Model.Nominee;
import Model.ProbableNominee;
import Model.Voter;
import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Header;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hardik
 */
public class GenerateReport implements Controller.Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        Date date = new Date();
        String email = (String) req.getSession().getAttribute("email");
        String elec_id = req.getParameter("id");
        String view = "index.jsp";
        String msg = null;
        String err = null;
        String title = "Login";
        System.out.println("");
        if (elec_id == null || elec_id.equals("") || email == null || email.equals("")) {
            err = "You are not logged in, or session already expired";
        } else {
            view = "Controller?action=view_elections"; // view changed if login successfull
            title = "Elections";
            long id = Long.parseLong(elec_id);
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                String filePath = File.separator + date.getTime() + ".pdf";
                String absoluteFilePath = req.getServletContext().getRealPath("/temp") + filePath;
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(absoluteFilePath));
                document.open();

                DBDAOImplElection objE = DBDAOImplElection.getInstance();
                DBDAOImplProbableNominee objP = DBDAOImplProbableNominee.getInstance();
                DBDAOImplNominee objN = DBDAOImplNominee.getInstance();
                DBDAOImplCandidate objC = DBDAOImplCandidate.getInstance();
                DBDAOImplVoter objV = DBDAOImplVoter.getInstance();

                Paragraph paragraph;
                Phrase phrase;
                Chunk chunk;
                Chapter chapter;
                Font fontChapterName = new Font(Font.HELVETICA, 25, Font.BOLD);
                Font fontTitle = new Font(Font.BOLD, 20);
                Font fontContent = new Font(Font.NORMAL, 12);
                PdfPTable table;
                PdfPCell cell;

                paragraph = new Paragraph();
                paragraph.setSpacingBefore(20f);
                phrase = new Phrase("Election Detail", fontChapterName);
                paragraph.add(phrase);
                chapter = new Chapter(paragraph, 1);
                document.add(chapter);

                Election election = objE.getElection(id);
                table = new PdfPTable(2);
                table.setWidthPercentage(100);
                table.setSpacingBefore(20f);
                table.setSpacingAfter(10f);

                cell = new PdfPCell(new Paragraph("Election Commissioner Email"));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(election.getElection_commissioner_email()));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Election Name"));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(election.getName()));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Description"));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(election.getDescription()));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Eligibility Requirements"));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(election.getRequirements()));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Election Type"));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(objE.getElectionType(election.getType_id()).getType()));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Created on"));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(election.getCreated_at() + ""));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Nomination started on:"));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(election.getNomination_start() + ""));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Nomination ended on:"));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(election.getNomination_end() + ""));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Withdrawal started on:"));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(election.getWithdrawal_start() + ""));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Withdrawal ended on:"));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(election.getWithdrawal_end() + ""));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Voting started on:"));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(election.getVoting_start() + ""));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Voting ended on:"));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(election.getVoting_end() + ""));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Petition duration:"));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(election.getPetition_duration() + ""));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                document.add(table);

                paragraph = new Paragraph();
                phrase = new Phrase("Probable Nominee Detail", fontChapterName);
                paragraph.add(phrase);
                chapter = new Chapter(paragraph, 2);
                document.add(chapter);

                ArrayList<ProbableNominee> pNominees = objP.getAllProbableNominees(id);
                table = new PdfPTable(2);
                table.setWidthPercentage(100);
                table.setSpacingBefore(20f);
                table.setSpacingAfter(10f);

                cell = new PdfPCell(new Paragraph("Email ID"));
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Status"));
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(10f);
                table.addCell(cell);
                String status = null;
                for (ProbableNominee pn : pNominees) {
                    cell = new PdfPCell(new Paragraph(pn.getEmail()));
                    cell.setBorder(PdfPCell.NO_BORDER);
                    cell.setPadding(10f);
//                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table.addCell(cell);
                    switch (pn.getStatus()) {
                        case 0:
                            status = "Link not sent";
                            break;
                        case 1:
                            status = "Link sent";
                            break;
                        case 2:
                            status = "Registered as Nominee";
                            break;
                    }
                    cell = new PdfPCell(new Paragraph(status));
                    cell.setBorder(PdfPCell.NO_BORDER);
                    cell.setPadding(10f);
//                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                }
                document.add(table);

                paragraph = new Paragraph();
                phrase = new Phrase("Nominee Detail", fontChapterName);
                paragraph.add(phrase);
                chapter = new Chapter(paragraph, 3);
                document.add(chapter);

                ArrayList<Nominee> nominees = objN.getNominees(id);
//              table = new PdfPTable(4);
                table = new PdfPTable(4);
                table.setWidthPercentage(100);
                table.setSpacingBefore(20f);
                table.setSpacingAfter(10f);

                cell = new PdfPCell(new Paragraph("Name"));
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(PdfPCell.NO_BORDER);
//                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Email ID"));
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
//                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Mobile No"));
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(PdfPCell.NO_BORDER);
//                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Status"));
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
//                cell.setPadding(10f);
                table.addCell(cell);
                /*
                 cell = new PdfPCell(new Paragraph("Photo"));
                 //                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                 cell.setBorder(PdfPCell.NO_BORDER);
                 //                cell.setPadding(10f);
                 table.addCell(cell);
                 */
                for (Nominee nominee : nominees) {
                    cell = new PdfPCell(new Paragraph(nominee.getFirstname() + " " + nominee.getLastname()));
                    cell.setBorder(PdfPCell.NO_BORDER);
//                    cell.setPadding(10f);
//                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(nominee.getEmail()));
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(PdfPCell.NO_BORDER);
//                cell.setPadding(10f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(nominee.getMobile()));
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(PdfPCell.NO_BORDER);
//                cell.setPadding(10f);
                    table.addCell(cell);

                    status = null;
                    switch (nominee.getStatus()) {
                        case 0:
                            status = "Waiting";
                            break;
                        case 1:
                            status = "Approved";
                            break;
                        case 2:
                            status = "Rejected";
                            break;
                        case 3:
                            status = "Withdrawn";
                            break;
                    }
                    cell = new PdfPCell(new Paragraph(status));
                    cell.setBorder(PdfPCell.NO_BORDER);
//                    cell.setPadding(10f);
//                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    /*
                     cell = new PdfPCell(Image.getInstance("../user_images/anonymous.png"), false);
                     cell.setBorder(PdfPCell.NO_BORDER);
                     //                    cell.setPadding(10f);
                     //                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                     table.addCell(cell);
                     */

                }
                document.add(table);

                paragraph = new Paragraph();
                phrase = new Phrase("Candidate Detail", fontChapterName);
                paragraph.add(phrase);
                chapter = new Chapter(paragraph, 4);
                document.add(chapter);
                ArrayList<Candidate> candidates = objC.getCandidates(id);
//              table = new PdfPTable(4);
                table = new PdfPTable(4);
                table.setWidthPercentage(100);
                table.setSpacingBefore(20f);
                table.setSpacingAfter(10f);

                cell = new PdfPCell(new Paragraph("Name"));
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(PdfPCell.NO_BORDER);
//                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Email ID"));
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
//                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Mobile No"));
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(PdfPCell.NO_BORDER);
//                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Votes"));
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(PdfPCell.NO_BORDER);
//                cell.setPadding(10f);
                table.addCell(cell);
                /*
                 cell = new PdfPCell(new Paragraph("Photo"));
                 //                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                 cell.setBorder(PdfPCell.NO_BORDER);
                 //                cell.setPadding(10f);
                 table.addCell(cell);
                 */
                for (Candidate candidate : candidates) {
                    cell = new PdfPCell(new Paragraph(candidate.getFirstname() + " " + candidate.getLastname()));
                    cell.setBorder(PdfPCell.NO_BORDER);
//                    cell.setPadding(10f);
//                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(candidate.getEmail()));
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(PdfPCell.NO_BORDER);
//                cell.setPadding(10f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(candidate.getMobile()));
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(PdfPCell.NO_BORDER);
//                cell.setPadding(10f);
                    table.addCell(cell);

                    cell = new PdfPCell(new Paragraph(candidate.getVotes() + ""));
                    cell.setBorder(PdfPCell.NO_BORDER);
//                    cell.setPadding(10f);
//                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                    /*
                     cell = new PdfPCell(Image.getInstance("../user_images/anonymous.png"), false);
                     cell.setBorder(PdfPCell.NO_BORDER);
                     //                    cell.setPadding(10f);
                     //                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                     table.addCell(cell);
                     */
                }
                document.add(table);

                paragraph = new Paragraph();
                phrase = new Phrase("Voter Detail", fontChapterName);
                paragraph.add(phrase);
                chapter = new Chapter(paragraph, 5);
                document.add(chapter);

                ArrayList<Voter> voters = objV.getVoters(id);
//              table = new PdfPTable(4);
                table = new PdfPTable(3);
                table.setWidthPercentage(100);
                table.setSpacingBefore(20f);
                table.setSpacingAfter(10f);

                cell = new PdfPCell(new Paragraph("Email ID"));
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(PdfPCell.NO_BORDER);
//                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Voter Link Status"));
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
//                cell.setPadding(10f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Voter Status"));
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
//                cell.setPadding(10f);
                table.addCell(cell);

                for (Voter voter : voters) {
                    cell = new PdfPCell(new Paragraph(voter.getEmail()));
                    cell.setBorder(PdfPCell.NO_BORDER);
//                    cell.setPadding(10f);
//                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table.addCell(cell);

                    if (voter.getLinkStatus()) {
                        status = "Link sent";
                    } else {
                        status = "Link not sent";
                    }

                    cell = new PdfPCell(new Paragraph(status));
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(PdfPCell.NO_BORDER);
//                cell.setPadding(10f);
                    table.addCell(cell);

                    if (voter.getStatus()) {
                        status = "Voted";
                    } else {
                        status = "Not Voted";
                    }

                    cell = new PdfPCell(new Paragraph(status));
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(PdfPCell.NO_BORDER);
//                cell.setPadding(10f);
                    table.addCell(cell);

                }
                document.add(table);

                paragraph = new Paragraph();
                phrase = new Phrase("Election Result", fontChapterName);
                paragraph.add(phrase);
                chapter = new Chapter(paragraph, 6);
                document.add(chapter);

                document.close();
                req.setAttribute("file_path", filePath);
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.OPEN)) {
                    System.out.println("Absolute File Path: " + absoluteFilePath);
                    desktop.open(new File(absoluteFilePath));
                } else {
                    err = "Open is not supported";
                }
            } catch (Exception ex) {
                err = ex.getMessage();
                System.out.println("Generate Report Error: " + ex.getMessage());
            }

        }
        req.setAttribute("msg", msg);
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        return view;
    }

}
